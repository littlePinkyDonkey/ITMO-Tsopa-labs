package andrei.teplyh.config;

import andrei.teplyh.entities.enums.AccountRoles;
import andrei.teplyh.security.jwt.config.JwtConfigurer;
import andrei.teplyh.security.jwt.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/account/signUp").permitAll()
                .antMatchers("/api/account/signIn").permitAll()

                .antMatchers("/admin").permitAll()

                .antMatchers("/api/temporary/create").hasRole(AccountRoles.USER.getSecurityRole())
                .antMatchers("/api/temporary/update").hasRole(AccountRoles.USER.getSecurityRole())
                .antMatchers("/api/temporary/all").hasAnyRole(AccountRoles.ADMIN.getSecurityRole(),
                                                                         AccountRoles.USER.getSecurityRole())

                .antMatchers("/api/file/published").permitAll()
                .antMatchers("/api/file/temporary").hasAnyRole(AccountRoles.ADMIN.getSecurityRole(),
                                                                          AccountRoles.USER.getSecurityRole())

                .antMatchers("/api/notification/send").hasRole(AccountRoles.ADMIN.getSecurityRole())
                .antMatchers("/api/notification/all").hasAnyRole(AccountRoles.USER.getSecurityRole(),
                                                                            AccountRoles.BANNED_USER.getSecurityRole())

                .antMatchers("/api/published/all").hasAnyRole(AccountRoles.USER.getSecurityRole(),
                                                                         AccountRoles.BANNED_USER.getSecurityRole())

                .anyRequest().authenticated()

                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/v2/api-docs/**",
                        "/swagger.json",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**"
                );
    }
}