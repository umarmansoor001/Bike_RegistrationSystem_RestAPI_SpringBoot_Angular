package com.globomatics.bike.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//   @Value("$auth0.apiAudience")
//   private String audience;
//
//   @Value("$auth0.issuer")
//   private String issuer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */
    	
    	http.csrf(csrf->csrf.disable())
    			.authorizeHttpRequests(auth -> auth.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/bikes")).permitAll()//.authenticated()
    					.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/bikes")).permitAll()//.authenticated()
    					.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/bikes/**")).permitAll()//.authenticated() 
    					//.requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
    					);
        return http.build();
    }
    
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
//    }

//    @Bean
//    JwtDecoder jwtDecoder() {
//        /*
//        By default, Spring Security does not validate the "aud" claim of the token, to ensure that this token is
//        indeed intended for our app. Adding our own validator is easy to do:
//        */
//
//        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
//                JwtDecoders.fromOidcIssuerLocation(issuer);
//
//        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
//        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
//        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
//
//        jwtDecoder.setJwtValidator(withAudience);
//
//        return jwtDecoder;
//    }
}