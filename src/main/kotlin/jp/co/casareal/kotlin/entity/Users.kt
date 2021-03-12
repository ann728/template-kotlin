package jp.co.casareal.kotlin.entity

import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Metamodel
import org.seasar.doma.Table

/**
 */
@Entity(listener = UsersListener::class, metamodel = Metamodel())
@Table(name = "users")
class Users : AbstractUsers() {

    /** */
    @Id
    @Column(name = "login_user")
    var loginUser: String? = null

    /** */
    @Column(name = "password")
    var password: String? = null

    /** */
    @Column(name = "name")
    var name: String? = null

    /** */
    @Column(name = "role_cd")
    var roleCd: String? = null
}
