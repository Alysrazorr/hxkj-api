package com.hxkj.security;

import com.hxkj.domain.User;
import com.hxkj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author dsd
 * @version 2018/6/21 14:36
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Autowired
    public CustomAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findFirstByUsernameEquals(username);

        if (null == user) {
            throw new AuthenticationCredentialsNotFoundException("找不到用户名");
        } else {
            if (!password.equalsIgnoreCase(user.getPassword())) {
                throw new BadCredentialsException("密码错误");
            } else {
                return new UsernamePasswordAuthenticationToken(username, password);
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
