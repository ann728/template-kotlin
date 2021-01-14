package jp.co.casareal.kotlin.service

import jp.co.casareal.kotlin.dao.UsersDao
import jp.co.casareal.kotlin.dto.UsersDto
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthService(
    private val usersDao: UsersDao
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val users = usersDao.findByLoginUser(username) ?: throw  UsernameNotFoundException("not found")

        return UsersDto(
            users.loginUser,
            users.name,
            users.password,
            users.roleCd
        )
    }
}
