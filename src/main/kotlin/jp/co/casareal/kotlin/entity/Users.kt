package jp.co.casareal.kotlin.entity

import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.Table

@Entity(immutable = true)
@Table(name = "users")
class Users (

    @Column(name = "login_user")
    val loginUser: String,

    val password: String,

    val name: String,

    @Column(name = "role_cd")
    val roleCd: String
)
