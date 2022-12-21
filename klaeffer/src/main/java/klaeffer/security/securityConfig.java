package klaeffer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {
    @Bean
    public SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(config ->
                        config.anyRequest().
                                authenticated()
                ).
                oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }
//    CLIENT_ID=71dd9933f5a1b8edef0f
//    CLIENT_SECRET=92be7740b5ca84ddf357d1b0083c58565509eac7

}
