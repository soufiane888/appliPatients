package com.example.patientsmvc.sec;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        PasswordEncoder passwordEncoder=passwordEncoder();
        String encodedPWD=passwordEncoder.encode("1234");
        auth.inMemoryAuthentication().withUser("user1").password(encodedPWD).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("2222")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("3333")).roles("USER","ADMIN");
    }


    protected void configure(HttpSecurity http) throws Exception{
        //http.formLogin().loginPage("/login");
        http.formLogin();
        http.authorizeRequests().anyRequest().authenticated();

    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
