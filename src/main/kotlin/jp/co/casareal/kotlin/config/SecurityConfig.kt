package jp.co.casareal.kotlin.config

import jp.co.casareal.kotlin.constants.RoleCd
import jp.co.casareal.kotlin.service.AuthService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfig(
    private val authService: AuthService
): WebSecurityConfigurerAdapter() {

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
            .mvcMatchers("/menu").hasAuthority(RoleCd.ADMIN.cd)
            .anyRequest().authenticated()

            /* ログイン設定 */
            .and()
            .formLogin()
            .defaultSuccessUrl("/", false)

            /* ログアウト設定 */
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(authService)
            .passwordEncoder(BCryptPasswordEncoder())
    }
}
