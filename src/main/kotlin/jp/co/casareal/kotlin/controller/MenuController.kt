package jp.co.casareal.kotlin.controller

import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MenuController {

    /** ログ */
    private val logger = LogFactory.getLog(MenuController::class.java)

    @GetMapping("/menu")
    fun menu() : String {
        logger.debug("### menu")

        return "menu"
    }
}
