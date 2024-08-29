package zack.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Authorizing & permitting every request
                .authorizeRequests()
                // White-listing these Uri
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                //
                .anyRequest()
                .authenticated()
                .and()
                //using the Basic Auth Protocol
                .httpBasic();
    }

}