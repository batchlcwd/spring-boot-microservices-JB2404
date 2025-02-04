package oauth.resource.server.oauth_resource_server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers(HttpMethod.GET, "/api/v1/users").permitAll()
                        .anyRequest().authenticated());


        //this is resource server: configure jwt ki configuration mujhe chahie
        http.oauth2ResourceServer(server ->
                server.jwt(Customizer.withDefaults()));


        return http.build();

    }

}
