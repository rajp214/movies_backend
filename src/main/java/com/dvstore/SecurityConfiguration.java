package com.dvstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.dvstore.services.CustomUserDetailsService;

@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetailsService userDetailsService;
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	public SimpleCorsFilter SimpleCorsFilter() {
		return new SimpleCorsFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
//		.configurationSource(request -> {
//			CorsConfiguration cors = new CorsConfiguration();
//		      cors.setAllowedOrigins(Arrays.asList("*"));
//		      cors.setAllowedMethods(Arrays.asList("*"));
//		      cors.setAllowedHeaders(Arrays.asList("*"));
//		      return cors;
//		    })
		.and()
		.csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		//.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
		.antMatchers("/auth/*","/getpassword/*").permitAll()
		.anyRequest().authenticated();

	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	//http.addFilterBefore(SimpleCorsFilter(), SessionManagementFilter.class);
	}
	
//	 @Bean
//	    public CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration config = new CorsConfiguration();
//	        config.setAllowCredentials(true);
//	        config.addAllowedOrigin("http://localhost:80");
//	        config.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", config);
//	        return source;
//	    }
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("*"));
	        configuration.setAllowCredentials(true);
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	       }
//	 @Bean
//	 public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
//	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	     CorsConfiguration config = new CorsConfiguration();
//	     config.setAllowCredentials(true);
//	     config.setAllowedOrigins(Collections.singletonList("*"));
//	     config.setAllowedMethods(Collections.singletonList("*"));
//	     config.setAllowedHeaders(Collections.singletonList("*"));
//	     source.registerCorsConfiguration("/**", config);
//	     FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//	     bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//	     return bean;
//	 }
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}