package klaeffer.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.servlet.SecurityMarker;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity chainBilder) throws Exception{
        chainBilder.authorizeHttpRequests(configure-> {
                    try {
                        configure.anyRequest()
                                .authenticated()
                                .and().csrf().disable();  //  #Here we can't post if we don't turn off csrf, which may be a bug.
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .oauth2Login(Customizer.withDefaults());
        return chainBilder.build();
    }
    //66b74b37af12046983ea
    //f75a0b49e8984a8a8b0e3d47f9342a2c05a6ae2c



}
