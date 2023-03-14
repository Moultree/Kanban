package com.example.kanban.security;

import com.example.kanban.DTO.CredentialsDTO;
import com.example.kanban.entity.User;
import com.example.kanban.service.AuthService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserAuthProvider implements AuthenticationProvider {

    private final AuthService authenticationService;

    public UserAuthProvider(AuthService authenticationService) {
        this.authenticationService = authenticationService;
    }
    //TODo change user to userdto
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = null;

        if (authentication instanceof UsernamePasswordAuthenticationToken){
            user = authenticationService.authenticate(new CredentialsDTO(
                    (String) authentication.getPrincipal(),
                    (char[]) authentication.getCredentials()
            ));
        } else if (authentication instanceof PreAuthenticatedAuthenticationToken){
            user = authenticationService.findByToken((String) authentication.getPrincipal());
        }

        if (user == null){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class <?> authentication) {
        return false;
    }
}
