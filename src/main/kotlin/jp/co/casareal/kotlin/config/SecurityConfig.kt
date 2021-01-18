package jp.co.casareal.kotlin.config

import jp.co.casareal.kotlin.constants.RoleCd
import jp.co.casareal.kotlin.service.AuthService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.session.HttpSessionEventPublisher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

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

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers("/", "/login").permitAll()
            .antMatchers("/menu").hasAuthority(RoleCd.ADMIN.cd)
            .anyRequest().authenticated()

            /* ログイン設定 */
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login/auth")
            .defaultSuccessUrl("/menu", true)
            .failureUrl("/login")
            .usernameParameter("username")
            .passwordParameter("password")

            /* ログアウト設定 */
            .and()
            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
            .deleteCookies("JSESSIONID") // クッキー削除
            .invalidateHttpSession(true) // HttpSessionを破棄

            /* セッション管理 */
            .and()
            .sessionManagement()
            .maximumSessions(1) // セッション数指定
            .expiredUrl("/login") // エラー画面設定
            .maxSessionsPreventsLogin(true) // true:先勝ち、false:後勝ち
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(authService)
            .passwordEncoder(BCryptPasswordEncoder())
    }

    @Bean
    fun httpSessionEventPublisher(): HttpSessionEventPublisher? {
        return HttpSessionEventPublisher()
    }
}
