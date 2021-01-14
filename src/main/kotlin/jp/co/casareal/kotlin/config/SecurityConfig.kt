package jp.co.casareal.kotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder

@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .mvcMatchers(
                "/css/**",
                "/js/**",
                "/img/**",
                "/webjars/**",
                "/favicon.ico")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers("/").permitAll()
            .mvcMatchers("/menu").hasRole("ADMIN")
            .anyRequest().authenticated()

            /* ログイン設定 */
            .and()
            .formLogin()
            .defaultSuccessUrl("/", true)

            /* ログアウト設定 */
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .withUser("user").password("user").roles("USER")
            .and().withUser("admin").password("admin").roles("ADMIN")
    }
}
