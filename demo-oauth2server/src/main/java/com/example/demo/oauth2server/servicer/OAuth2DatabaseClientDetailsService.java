package com.example.demo.oauth2server.servicer;


import com.example.demo.oauth2server.entity.*;
import com.example.demo.oauth2server.repository.ClientDetailsRepository;
import com.example.demo.oauth2server.repository.GrantTypeRepository;
import com.example.demo.oauth2server.repository.ResourceRepository;
import com.example.demo.oauth2server.repository.ScopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class OAuth2DatabaseClientDetailsService implements ClientDetailsService, ClientRegistrationService {


    private final Function<? super ClientDetailsEntity, ? extends BaseClientDetails> entityToDomain = entity -> {
        BaseClientDetails clientDetails = new BaseClientDetails();

        clientDetails.setClientId(entity.getClientId());
        clientDetails.setClientSecret(entity.getClientSecret());

        clientDetails.setAccessTokenValiditySeconds(entity.getAccessTokenValiditySeconds());
        clientDetails.setRefreshTokenValiditySeconds(entity.getRefreshTokenValiditySeconds());

        clientDetails.setAuthorizedGrantTypes(entity.getGrantTypeEntities().stream().map(GrantTypeEntity::getValue).collect(Collectors.toList()));

        clientDetails.setScope(entity.getScopeEntities().stream().map(ScopeEntity::getValue).collect(Collectors.toList()));

        clientDetails.setAutoApproveScopes(entity.getScopeEntities().stream().filter(ScopeEntity::getAutoApprove).map(ScopeEntity::getValue).collect(Collectors.toList()));

        clientDetails.setResourceIds(entity.getResourceEntities().stream().map(ResourceEntity::getValue).collect(Collectors.toList()));

        clientDetails.setRegisteredRedirectUri(entity.getRedirectUris().stream().map(RedirectUriEntity::getValue).collect(Collectors.toSet()));

        clientDetails.setAdditionalInformation(Collections.<String, Object>emptyMap());

        return clientDetails;
    };
    @Autowired
    private ClientDetailsRepository clientDetailsRepository;
    @Autowired
    private GrantTypeRepository grantTypeRepository;
    @Autowired
    private ScopeRepository scopeRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsRepository.findOneByClientId(clientId).map(entityToDomain).<ClientRegistrationException>orElseThrow(() -> new NoSuchClientException("Client ID not found"));
    }

    @Transactional
    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        if (clientDetailsRepository.findOneByClientId(clientDetails.getClientId()).isPresent()) {
            throw new ClientAlreadyExistsException("Client ID already exists");
        }

        ClientDetailsEntity clientDetailsEntity = ClientDetailsEntity.builder()//
                .clientId(clientDetails.getClientId())//
                .clientSecret(clientDetails.getClientSecret())//
                .accessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds())//
                .refreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds()).build();

        clientDetailsEntity.setGrantTypeEntities(clientDetails.getAuthorizedGrantTypes().stream().map(grantType -> grantTypeRepository.findOneByValue(grantType)
//                .map(grantTypeEntity -> ClientDetailsToAuthorizedGrantTypeXrefEntity.builder().clientDetails(clientDetailsEntity).grantType(grantTypeEntity).build())
                        .<ClientRegistrationException>orElseThrow(() -> new ClientRegistrationException("Unsupported grant type: "
                                + grantType))
        ).collect(Collectors.toSet()));

        clientDetailsEntity.setScopeEntities(clientDetails.getScope().stream().map(scope -> scopeRepository.findOneByValue(scope)
//                .map(scopeEntity -> ClientDetailsToScopesXrefEntity.builder().clientDetails(clientDetailsEntity).scope(scopeEntity).autoApprove(clientDetails.isAutoApprove(scope)).build())
                .<ClientRegistrationException>orElseThrow(() -> new ClientRegistrationException("Unknown scope: "
                + scope))).collect(Collectors.toSet()));

        clientDetailsEntity.setResourceEntities(clientDetails.getResourceIds().stream().map(resourceId -> resourceRepository.findOneByValue(resourceId)
                //.map(resourceIdEntity -> ClientDetailsToResourceIdXrefEntity.builder().clientDetails(clientDetailsEntity).resourceId(resourceIdEntity).build())
                .<ClientRegistrationException>orElseThrow(() -> new ClientRegistrationException("Unknown resource id: "
                + resourceId))).collect(Collectors.toSet()));

        clientDetailsEntity.setRedirectUris(clientDetails.getRegisteredRedirectUri().stream().map(redirectUri -> RedirectUriEntity.builder().clientDetails(clientDetailsEntity).value(redirectUri).build()).collect(Collectors.toSet()));

        clientDetailsRepository.save(clientDetailsEntity);

    }

    @Transactional
    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

        ClientDetailsEntity entity = clientDetailsRepository.findOneByClientId(clientDetails.getClientId()).orElseThrow(() -> new NoSuchClientException("Client details not found."));

        entity.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
        entity.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds());

        // merge grant type
        Set<GrantTypeEntity> grantTypeXrefEntityRemoves = entity.getGrantTypeEntities().stream().filter(grantTypeEntity -> !clientDetails.getAuthorizedGrantTypes().contains(grantTypeEntity.getValue())).collect(Collectors.toSet());

        Set<String> grantTypeOriginValueSet = entity.getGrantTypeEntities().stream().map(grantTypeEntity -> grantTypeEntity.getValue()).collect(Collectors.toSet());
        Set<GrantTypeEntity> grantTypeXrefEntityNewOnes = clientDetails.getAuthorizedGrantTypes().stream().filter(grantType -> !grantTypeOriginValueSet.contains(grantType)).map(grantType -> grantTypeRepository.findOneByValue(grantType)
                //.map(grantTypeEntity -> ClientDetailsToAuthorizedGrantTypeXrefEntity.builder().clientDetails(entity).grantType(grantTypeEntity).build())
                .<ClientRegistrationException>orElseThrow(() -> new ClientRegistrationException("Unsupported grant type: "
                + grantType))).collect(Collectors.toSet());

        entity.getGrantTypeEntities().removeAll(grantTypeXrefEntityRemoves);
        entity.getGrantTypeEntities().addAll(grantTypeXrefEntityNewOnes);

        // merge scopes
        Set<ScopeEntity> scopeXrefEntityRemoves = entity.getScopeEntities().stream().filter(scopeEntity -> !clientDetails.getScope().contains(scopeEntity.getValue())).collect(Collectors.toSet());

        Set<String> scopeOriginValueSet = entity.getScopeEntities().stream().map(scopeEntity -> scopeEntity.getValue()).collect(Collectors.toSet());
        Set<ScopeEntity> scopeXrefEntityNewOnes = clientDetails.getScope().stream().filter(scope -> !scopeOriginValueSet.contains(scope)).map(scope -> scopeRepository.findOneByValue(scope).filter(ScopeEntity::getAutoApprove)
                //.map(scopeEntity -> ClientDetailsToScopesXrefEntity.builder().clientDetails(entity).scope(scopeEntity).autoApprove(clientDetails.isAutoApprove(scope)).build())
                .<ClientRegistrationException>orElseThrow(() -> new ClientRegistrationException("Unknown scope: "
                + scope))).collect(Collectors.toSet());

        entity.getScopeEntities().removeAll(scopeXrefEntityRemoves);
        entity.getScopeEntities().forEach(scopeEntity -> scopeEntity.setAutoApprove(clientDetails.isAutoApprove(scopeEntity.getValue())));
        entity.getScopeEntities().addAll(scopeXrefEntityNewOnes);

        // merge resource id
        Set<ResourceEntity> resourceIdXrefEntityRemoves = entity.getResourceEntities().stream().filter(resourceEntity -> !clientDetails.getResourceIds().contains(resourceEntity.getValue())).collect(Collectors.toSet());

        Set<String> resIdOriginValueSet = entity.getResourceEntities().stream().map(resourceEntity -> resourceEntity.getValue()).collect(Collectors.toSet());
        Set<ResourceEntity> resIdXrefEntityNewOnes = clientDetails.getResourceIds().stream().filter(resId -> !resIdOriginValueSet.contains(resId)).map(resId -> resourceRepository.findOneByValue(resId)
                //.map(resourceIdEntity -> ClientDetailsToResourceIdXrefEntity.builder().clientDetails(entity).resourceId(resourceIdEntity).build())
                .<ClientRegistrationException>orElseThrow(() -> new ClientRegistrationException("Unknown resource id: "
                + resId))).collect(Collectors.toSet());

        entity.getResourceEntities().removeAll(resourceIdXrefEntityRemoves);
        entity.getResourceEntities().addAll(resIdXrefEntityNewOnes);

        // merge redirect uri
        Set<RedirectUriEntity> redirectUriEntityRemoves = entity.getRedirectUris().stream().filter(redirectUriEntity -> !clientDetails.getRegisteredRedirectUri().contains(redirectUriEntity.getValue())).collect(Collectors.toSet());

        Set<String> originRedirectUrisValue = entity.getRedirectUris().stream().map(RedirectUriEntity::getValue).collect(Collectors.toSet());
        Set<RedirectUriEntity> redirectUriEntityNewOnes = clientDetails.getRegisteredRedirectUri().stream().filter(redirectUri -> !originRedirectUrisValue.contains(redirectUri)).map(redirectUri -> RedirectUriEntity.builder().clientDetails(entity).value(redirectUri).build()).collect(Collectors.toSet());

        entity.getRedirectUris().removeAll(redirectUriEntityRemoves);
        entity.getRedirectUris().addAll(redirectUriEntityNewOnes);

        // save
        clientDetailsRepository.save(entity);
    }

    @Transactional
    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        ClientDetailsEntity clientDetailsEntity = clientDetailsRepository.findOneByClientId(clientId).<NoSuchClientException>orElseThrow(() -> new NoSuchClientException("Client id not found."));

        clientDetailsEntity.setClientSecret(passwordEncoder.encode(secret));

        clientDetailsRepository.save(clientDetailsEntity);
    }

    @Transactional
    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {

        ClientDetailsEntity entityToRemove = clientDetailsRepository.findOneByClientId(clientId).<NoSuchClientException>orElseThrow(() -> new NoSuchClientException("Client id not found."));

        clientDetailsRepository.delete(entityToRemove);

    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return clientDetailsRepository.findAll().stream().map(entityToDomain).collect(Collectors.toList());
    }

}
