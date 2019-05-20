package nc.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;

@Configuration
@Order(99)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/events/sort, /events/types, /events/create," +
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