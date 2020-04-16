package com.anhvv.base.security.provider;

import com.anhvv.base.common.RoleType;
import com.anhvv.base.entity.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {

    private static List<User> users = new ArrayList();

    public UsernameAndPasswordAuthenticationProvider() {
        User u1 = new User("erin", "123", new ArrayList<>());
        u1.getUserRole().add(RoleType.ADMIN);
        users.add(u1);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        if (!(credentials instanceof String)) {
            return null;
        }
        String password = credentials.toString();

        Optional<User> userOptional = users.stream()
                .filter(u -> u.match(name, password))
                .findFirst();

        if (!userOptional.isPresent()) {
            throw new BadCredentialsException("Authentication failed for " + name);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ userOptional.get().getUserRole()));
        return new
                UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
