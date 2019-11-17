package cn.ljtnono.re.config;

import cn.ljtnono.re.service.IReUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security 配置
 *
 * @author 凌家童
 * @version 1.0
 * @date 2019/10/6
 */
@SpringBootConfiguration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IReUserService iReUserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("fore/**")
                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/admin/login")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/admin/login").permitAll()
//                .antMatchers("/admin/**").authenticated()
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(iReUserService);
    }
}
