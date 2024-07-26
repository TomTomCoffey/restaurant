package learn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = {"http://localhost:3000"})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable(); // 1



        http.cors().and().authorizeRequests() // 2
                .antMatchers("/user/signup", "/", "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/signup", "/api/user/login").permitAll()
                .antMatchers("/refresh_token").authenticated()
                .antMatchers(HttpMethod.GET, "/api/item", "/api/item/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/item").hasAnyRole( "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/item/*").hasAnyRole( "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/item/*").hasAnyRole( "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/user/*").hasAnyRole( "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/user/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/user/*").hasRole("ADMIN")
                .antMatchers("/api/admin").hasRole("ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                
                .and()
               // .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement() // 4
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /*@Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }*/

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }



}
