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

    /** 一覧画面 */
    @GetMapping("/index")
    fun index(model: Model): String {
        model.addAttribute("employeeList", employeeService.findAll())
        return "employee/index"
    }

    /** 詳細画面 */
    @GetMapping("/findById")
    fun findById(
        @RequestParam id: Int,
        model: Model
    ): String {
        model.addAttribute("employee", employeeService.findById(id))
        return "employee/findById"
    }

    /** 新規登録画面 */
    @GetMapping("/insertMain")
    fun insertMain(model: Model): String {
        model.addAttribute("employeeForm", EmployeeForm())
        return "employee/insertMain"
    }

    /** 新規登録処理 */
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

    /** 削除処理 */
    @PostMapping("/delete")
    fun delete(@RequestParam id: Int): String {
        employeeService.deleteById(id)
        return "redirect:/employee/index"
    }
}