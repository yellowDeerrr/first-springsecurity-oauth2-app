package dev.danvega.sociallogin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "Hello Home!";
    }
    @GetMapping("/secured")
    public String secured(){
        return "Hello Secure!";
    }

    @GetMapping("/info")
    public String name(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof OAuth2AuthenticatedPrincipal) {
            OAuth2AuthenticatedPrincipal oauth2Principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

            // Access the user attributes
            String userId = oauth2Principal.getAttribute("id");
            String name = oauth2Principal.getAttribute("name");
            String email = oauth2Principal.getAttribute("email");
            // other necessary user data
            return userId + name + email;
        }
        return "n";
    }
}
