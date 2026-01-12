package jp.co.casareal.kotlin.web.form

import jp.co.casareal.kotlin.entity.Employee
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EmployeeForm(

    @field:NotBlank
    val name: String? = null,

    @field:NotNull
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val joinedDate: LocalDate? = null,

    @field:NotBlank
    val departmentName: String? = null,

    @field:NotBlank
    @field:Email
    val email: String? = null,

    @field:NotNull
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val birthDay: LocalDate? = null
) {
    /** Form → Entity 変換 */
    fun toEntity(): Employee =
        Employee(
            id = null,
            name = name!!,
            joinedDate = joinedDate!!,
            departmentName = departmentName!!,
            email = email!!,
            birthDay = birthDay!!
        )
}