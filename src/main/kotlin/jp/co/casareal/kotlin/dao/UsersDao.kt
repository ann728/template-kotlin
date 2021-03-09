package jp.co.casareal.kotlin.dao

import jp.co.casareal.kotlin.entity.Users
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable

/**
 */
@Dao
@ConfigAutowireable
interface UsersDao {

    /**
     * @param loginUser
     * @return the Users entity
     */
    @Select
    fun selectById(loginUser: String): Users

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: Users): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: Users): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: Users): Int
}