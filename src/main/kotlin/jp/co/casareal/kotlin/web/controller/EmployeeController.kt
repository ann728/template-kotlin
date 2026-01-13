package jp.co.casareal.kotlin.web.controller



import jp.co.casareal.kotlin.service.EmployeeService
import jp.co.casareal.kotlin.web.form.EmployeeForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/employee")
class EmployeeController(
    private val employeeService: EmployeeService
) {


    @GetMapping("/index")
    fun index(model: Model): String {
        model.addAttribute("employeeList", employeeService.findAll())
        return "employee/index"
    }


    @GetMapping("/findById")
    fun findById(
        @RequestParam id: Int,
        model: Model
    ): String {
        model.addAttribute("employee", employeeService.findById(id))
        return "employee/findById"
    }


    @GetMapping("/insertMain")
    fun insertMain(model: Model): String {
        model.addAttribute("employeeForm", EmployeeForm())
        return "employee/insertMain"
    }


    @PostMapping("/insertComplete")
    fun insertComplete(
        @Valid @ModelAttribute employeeForm: EmployeeForm,
        bindingResult: BindingResult,
        model: Model
    ): String {

        if (bindingResult.hasErrors()) {
            return "employee/insertMain"
        }

        employeeService.insert(employeeForm.toEntity())
        return "redirect:/employee/index"
    }


    @PostMapping("/deleteComplete")
    fun delete(@RequestParam id: Int): String {
        employeeService.deleteById(id)
        return "redirect:/employee/index"
    }

    @GetMapping("/deleteConfirm")
    fun deleteConfirm(@RequestParam id: Int, model: Model): String {
        val employee = employeeService.findById(id)
        model.addAttribute("employee", employee)
        return "employee/deleteConfirm"
    }

    @GetMapping("/updateMain")
    fun updateMain(
        @RequestParam id: Int,
        model: Model
    ): String {
        val employee = employeeService.findById(id)

        val employeeForm = EmployeeForm.fromEntity(employee)

        model.addAttribute("employeeForm", employeeForm)
        model.addAttribute("employeeId", id) // 画面のID表示用
        return "employee/updateMain"
    }

    @PostMapping("/updateComplete")
    fun updateComplete(
        @Valid @ModelAttribute employeeForm: EmployeeForm,
        bindingResult: BindingResult,
        model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeId", employeeForm.id)
            return "employee/updateMain"
        }

        employeeService.update(employeeForm.toEntity())
        return "redirect:/employee/index"
    }
}