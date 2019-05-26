package nc.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceSecurityConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/updates/by_event")
                .permitAll()
                .antMatchers("/events/create," +
                        " /events/update/{eventId:\\d+}, /events/delete/{eventId:\\d+}, /updates/**")
                .authenticated()
                .anyRequest()
                .permitAll()
        .and()
                .csrf()
                .disable()
                .httpBasic()
                .disable();
    }
}