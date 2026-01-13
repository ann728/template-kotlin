package jp.co.casareal.kotlin.entity

import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.GeneratedValue
import org.seasar.doma.GenerationType
import org.seasar.doma.Column
import org.seasar.doma.SequenceGenerator
import org.seasar.doma.Table
import java.time.LocalDate

@Entity(immutable = true)
@Table(name = "employee")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "employee_id_seq")
    val id: Int? = null,

    val name: String,

    @Column(name = "joined_date")
    val joinedDate: LocalDate,

    @Column(name = "department_name")
    val departmentName: String,

    val email: String,

    @Column(name = "birth_day")
    val birthDay: LocalDate
)