package pl.springboot.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//
				.antMatchers("/admin/**").access("hasRole('ADMIN')")//
				.anyRequest().permitAll()//
				.and()//
				.formLogin().loginPage("/")//
				.defaultSuccessUrl("/")//
				.failureUrl("/?errorLogin=true")//
				.usernameParameter("uname").passwordParameter("psw")//
				.and()//
				.logout().logoutSuccessUrl("/?logout=true")//
				.and()//
				.exceptionHandling().accessDeniedPage("/denide");//
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN")
				.and().withUser("mateusz").password("{noop}password").roles("USER");
	}
}
