package jp.co.casareal.kotlin.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RootController {
    @GetMapping("/")
    fun root(): String {
        return "redirect:employee/index"
    }
}
