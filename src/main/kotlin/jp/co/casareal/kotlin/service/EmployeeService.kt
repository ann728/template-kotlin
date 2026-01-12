package jp.co.casareal.kotlin.service
import jp.co.casareal.kotlin.entity.Employee

public interface EmployeeService {
    fun findAll(): List<Employee>

    fun findById(id: Int): Employee

    fun insert(employee: Employee)

    fun update(employee: Employee)

    fun deleteById(id: Int)
}