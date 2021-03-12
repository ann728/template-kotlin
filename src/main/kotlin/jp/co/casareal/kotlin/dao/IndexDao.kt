package jp.co.casareal.kotlin.dao

import jp.co.casareal.kotlin.entity.Index
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable
/**
 */
@Dao
@ConfigAutowireable
interface IndexDao {

    /**
     * @param id
     * @return the Index entity
     */
    @Select
    fun selectById(id: Int): Index

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: Index): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: Index): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: Index): Int
}