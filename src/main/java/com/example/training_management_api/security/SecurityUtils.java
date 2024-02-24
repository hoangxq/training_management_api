package com.example.training_management_api.security;

import com.example.training_management_api.exception.EntityNotFoundException;
import com.example.training_management_api.model.User;
import com.example.training_management_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;

    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    private static String extractPrincipal(Authentication authentication) {

        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }

        return null;
    }

    public Optional<User> getCurrentUserJwt() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional
                .ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails userDetail) {
                        return userRepository.findByUsername(userDetail.getUsername())
                                .orElseThrow(() -> new EntityNotFoundException(User.class.toString(), userDetail.getUsername()));
                    }
                    return null;
                });
    }
}
