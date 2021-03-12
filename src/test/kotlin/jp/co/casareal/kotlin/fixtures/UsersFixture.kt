package jp.co.casareal.kotlin.fixtures

import com.ninja_squad.dbsetup_kotlin.DbSetupBuilder
import com.ninja_squad.dbsetup_kotlin.mappedValues

data class UsersFixture (
    val login_user: String = "", 
    val password: String = "", 
    val name: String = "", 
    val role_cd: String = "" 
)

fun DbSetupBuilder.insertUsersFixture(f: UsersFixture) {
    insertInto("users") {
        mappedValues(
                "login_user" to f.login_user,
                "password" to f.password,
                "name" to f.name,
                "role_cd" to f.role_cd
        )
    }
}