package com.example.lab2.domain;
import com.example.lab2.domain.security.jwt.AuthEntryPointJwt;
import com.example.lab2.domain.security.jwt.AuthTokenFilter;
import com.example.lab2.domain.security.services.ClientDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /** Spring Security will load User details to perform authentication & authorization.
     * So it has UserDetailsService interface that we need to implement.
     * The implementation of UserDetailsService will be used for configuring DaoAuthenticationProvider
     * by AuthenticationManagerBuilder.userDetailsService() method.
     * We also need a PasswordEncoder for the DaoAuthenticationProvider. If we don???t specify, it will use plain text. **/
    @Autowired
    ClientDetailsServiceImpl clientDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(clientDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** We override the configure(HttpSecurity http) method from WebSecurityConfigurerAdapter interface. It tells Spring Security how
     * we configure CORS and CSRF, when we want to require all users to be authenticated or not, which filter (AuthTokenFilter) and when
     *  we want it to work (filter before UsernamePasswordAuthenticationFilter), which Exception Handler is chosen (AuthEntryPointJwt). **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*", "/docs/**", "/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
