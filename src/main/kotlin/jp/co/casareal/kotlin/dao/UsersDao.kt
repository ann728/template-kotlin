package jp.co.casareal.kotlin.dao

import jp.co.casareal.kotlin.entity.Users
import org.seasar.doma.Dao
import org.seasar.doma.Select
import org.seasar.doma.Sql
import org.seasar.doma.boot.ConfigAutowireable

@Dao
@ConfigAutowireable
interface UsersDao {

    @Select
//    @Sql("select * from users where login_user = /* login_user */'1'")
    fun findByLoginUser(login_user : String): Users?
}
