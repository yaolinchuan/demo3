package com.example.demo.oauth2server.servicer;

import com.example.demo.oauth2server.repository.ClientDetailsRepository;
import com.example.demo.oauth2server.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DatabaseUserDetailService implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username).map(userEntity -> //
                new User(userEntity.getUsername(), //
                        userEntity.getPassword(),
                        !userEntity.isDisabled(),
                        !userEntity.isAccountNonExpired(),
                        !userEntity.isCredentialsNonExpired(),
                        !userEntity.isAccountNonLocked(),//
                        userEntity.getRoles().stream().map(userRole -> //
                                new SimpleGrantedAuthority(prefixRoleName(userRole.getName())))//
                                .collect(Collectors.toList())
                ))//
                .orElseThrow(() -> new UsernameNotFoundException("User " + username
                        + " was not found in the database"));
    }

//    public Triple<Long, String, Long> loadClientByToken(String tokenId) {
//        String clientId = accessTokenRepository.findOneByTokenId(tokenId)//
//                .map(accessTokenEntity -> accessTokenEntity.getClientId())//
//                .orElseThrow(() -> new UsernameNotFoundException("Token " + tokenId
//                        + " was not found in the database"));
//        ClientDetailsEntity details = clientDetailsRepository.findOneByClientId(clientId).get();
//        ClientDetailsLimitEntity clientLimit = details.getClientLimit();
//
//        return new ImmutableTriple<Long, String, Long>(clientLimit.getIntervalInMills(), clientId,
//                clientLimit.getLimits());
//
//    }

    private String prefixRoleName(String roleName) {
        if (!StringUtils.isEmpty(roleName) && !roleName.startsWith(ROLE_PREFIX)) {
            return ROLE_PREFIX + roleName;
        }
        return roleName;
    }
}
