import jp.co.casareal.kotlin.dao.EmployeeDao
import jp.co.casareal.kotlin.entity.Employee
import jp.co.casareal.kotlin.service.EmployeeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeServiceImpl(
    private val employeeDao: EmployeeDao
) : EmployeeService {

    @Transactional(readOnly = true)
    override fun findAll(): List<Employee> =
        employeeDao.selectAll()

    @Transactional(readOnly = true)
    override fun findById(id: Int): Employee =
        employeeDao.selectById(id)
            ?: throw NoSuchElementException("社員が存在しません (id=$id)")

    @Transactional
    override fun insert(employee: Employee) {
        val id = employee.id
            ?: run {
                employeeDao.insert(employee)
                return
            }

        throw IllegalArgumentException("新規登録時にIDは指定できません (id=$id)")
    }

    @Transactional
    override fun update(employee: Employee) {
        val id = employee.id
            ?: throw IllegalArgumentException("更新時にIDは必須です")

        val count = employeeDao.update(employee)
        if (count <= 0) {
            throw IllegalStateException("更新対象が存在しません")
        }
    }

    @Transactional
    override fun deleteById(id: Int) {
        val count = employeeDao.deleteById(id);
        if (count <= 0) {
            throw IllegalStateException("更新対象が存在しません")
        }
    }
}
