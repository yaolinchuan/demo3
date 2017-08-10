package com.example.demo.oauth2server.config;

import com.example.demo.oauth2server.entity.*;
import com.example.demo.oauth2server.repository.ClientDetailsRepository;
import com.example.demo.oauth2server.repository.GrantTypeRepository;
import com.example.demo.oauth2server.repository.ResourceIdRepository;
import com.example.demo.oauth2server.repository.ScopeRepository;
import com.example.demo.oauth2server.servicer.OAuth2DatabaseClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.loader.ResourceEntry;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.resource.jdbc.ResourceRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by liyuhong on 2017/8/8.
 */
@Configuration
@Profile("dev")
@Slf4j
public class DefaultClientDetailsConfiguration implements InitializingBean {

    private static final String[] DEFAULT_GRANT_TYPES = {"authorization_code", "refresh_token",
            "password", "client_credentials"};

    private static final String API_GRANT_TYPES = StringUtils.join(DEFAULT_GRANT_TYPES, ",");

    private static final String[] DEFAULT_SCOPES = {"read", "write", "trust"};

    private static final String API_SCOPES = StringUtils.join(DEFAULT_SCOPES, ",");
    private static final String[] DEFAULT_RESOURCES = {"oauth2resource", "apigateway"};
    private static final String API_RESOURCES = StringUtils.join(DEFAULT_RESOURCES, ",");

    @Autowired
    private GrantTypeRepository grantTypeRepository;

    @Autowired
    private ScopeRepository scopeRepository;
    @Autowired
    private ResourceIdRepository resourceIdRepository;

    @Autowired
    private OAuth2DatabaseClientDetailsService oAuth2DatabaseClientDetailsService;

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (grantTypeRepository.count() == 0) {
            grantTypeRepository.save(Arrays.stream(DEFAULT_GRANT_TYPES)//
                    .map(grantType -> GrantTypeEntity.builder().value(grantType).build())//
                    .collect(Collectors.toList()));
        }
        if (scopeRepository.count() == 0) {
            scopeRepository.save(Arrays.stream(DEFAULT_SCOPES)//
                    .map(scope -> ScopeEntity.builder().value(scope).build())//
                    .collect(Collectors.toList()));
        }
        if (resourceIdRepository.count() == 0) {
            resourceIdRepository.save(Arrays.stream(DEFAULT_RESOURCES)//
                    .map(resourceId -> ResourceIdEntity.builder().value(resourceId).build())//
                    .collect(Collectors.toList()));
        }

        BaseClientDetails clientDetails = new BaseClientDetails("acme", API_RESOURCES, API_SCOPES, API_GRANT_TYPES, null);
        clientDetails.setClientSecret("acmesecret");
        clientDetails.setRegisteredRedirectUri(Collections.emptySet());
        try {
            oAuth2DatabaseClientDetailsService.addClientDetails(clientDetails);
        } catch (ClientAlreadyExistsException e) {
            log.warn(e.getMessage());
        }

        clientDetails = new BaseClientDetails("open_api", null, API_SCOPES, API_GRANT_TYPES, null);
        clientDetails.setClientSecret("open_api");
        clientDetails.setRegisteredRedirectUri(Collections.emptySet());
        try {
            oAuth2DatabaseClientDetailsService.addClientDetails(clientDetails);
            // 每隔open_api 10秒内最多只能调用3次api
            ClientDetailsEntity detailEntity = clientDetailsRepository.findOneByClientId(clientDetails.getClientId()).get();
            ClientDetailsLimitEntity limitEntity = ClientDetailsLimitEntity.builder().intervalInMills(10000L).limits(3L).build();
            detailEntity.setClientLimit(limitEntity);
            limitEntity.setClientDetail(detailEntity);
            clientDetailsRepository.save(detailEntity);
        } catch (ClientAlreadyExistsException e) {
            log.warn(e.getMessage());
        }

    }
}
