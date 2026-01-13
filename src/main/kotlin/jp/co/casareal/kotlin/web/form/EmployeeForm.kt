package jp.co.casareal.kotlin.web.form

import jp.co.casareal.kotlin.entity.Employee
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EmployeeForm(
    val id: Int? = null,

    @field:NotBlank(message = "氏名を入力してください")
    val name: String? = null,

    @field:NotNull(message = "入社年月日を選択してください")
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val joinedDate: LocalDate? = null,

    @field:NotBlank(message = "部署名を入力してください")
    val departmentName: String? = null,

    @field:NotBlank(message = "メールアドレスを入力してください")
    @field:Email(message = "メールアドレスの形式が正しくありません")
    val email: String? = null,

    @field:NotNull(message = "誕生日を選択してください")
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val birthDay: LocalDate? = null
) {
    companion object {
        fun fromEntity(e: Employee): EmployeeForm {
            return EmployeeForm(
                id = e.id,
                name = e.name,
                joinedDate = e.joinedDate,
                departmentName = e.departmentName,
                email = e.email,
                birthDay = e.birthDay
            )
        }
    }
    fun toEntity(): Employee =
        Employee(
            id = this.id,
            name = name!!,
            joinedDate = joinedDate!!,
            departmentName = departmentName!!,
            email = email!!,
            birthDay = birthDay!!
        )
}