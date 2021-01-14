package jp.co.casareal.kotlin.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

data class UsersDto(
    val id: String,
    val name: String,
    val pass: String,
    val roleCode: String
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(roleCode)
    }

    override fun getUsername(): String {
        return this.name
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}
