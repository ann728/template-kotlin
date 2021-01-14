package jp.co.casareal.kotlin.dao

import org.seasar.doma.Dao
import org.seasar.doma.Select
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.experimental.Sql

@Dao
@ConfigAutowireable
interface IndexDao {

    @Select
    @Sql("select name from index where id=1")
    fun index(): String
}
