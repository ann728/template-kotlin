package jp.co.casareal.kotlin.dao

import jp.co.casareal.kotlin.entity.Employee
import org.seasar.doma.AnnotateWith
import org.seasar.doma.Annotation
import org.seasar.doma.AnnotationTarget
import org.seasar.doma.Dao
import org.seasar.doma.Delete
import org.seasar.doma.Insert
import org.seasar.doma.Select
import org.seasar.doma.Update
import org.springframework.stereotype.Repository
import org.seasar.doma.jdbc.Result


@Dao
@AnnotateWith(annotations = [
    Annotation(target = AnnotationTarget.CLASS, type = Repository::class)
])
interface EmployeeDao {
    @Select
    fun selectAll(): List<Employee>

    @Select
    fun selectById(id: Int): Employee?

    @Insert
    fun insert(employee: Employee): Result<Employee>

    @Update
    fun update(employee: Employee): Result<Employee>

    @Delete(sqlFile = true)
    fun deleteById(id: Int): Int
}
