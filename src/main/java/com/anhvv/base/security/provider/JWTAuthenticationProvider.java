package com.anhvv.base.security.provider;

import com.anhvv.base.security.authentication.JWTAuthenticationToken;
import com.anhvv.base.security.session.SessionCache;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider  implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object user = SessionCache.getAttribute(((JWTAuthenticationToken) authentication).getToken());
        if(user instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) user;
            return new JWTAuthenticationToken(usernamePasswordAuthenticationToken.getAuthorities(), usernamePasswordAuthenticationToken.getPrincipal(),
                    usernamePasswordAuthenticationToken.getCredentials());

        }
        throw new BadCredentialsException("Authentication failed because token is null");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthenticationToken.class);
    }
}
