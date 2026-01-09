package jp.co.casareal.kotlin.dao

import jp.co.casareal.kotlin.entity.Employee
import org.seasar.doma.Dao
import org.seasar.doma.Delete
import org.seasar.doma.Insert
import org.seasar.doma.Select
import org.seasar.doma.Update

@Dao
interface EmployeeDao {
    @Select
    fun selectAll(): List<Employee>

    @Select
    fun selectById(id: Int): Employee?

    @Insert
    fun insert(employee: Employee): Int

    @Update
    fun update(employee: Employee): Int

    @Delete
    fun delete(employee: Employee): Int
}
