package jp.co.casareal.kotlin.controller

import jp.co.casareal.kotlin.service.IndexService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController(@Autowired val indexService: IndexService) {

    /** ログ */
    private val logger = LogFactory.getLog(IndexController::class.java)

    @GetMapping("", "/")
    fun index() : String {
        logger.debug("### index")

        logger.debug("### name:" + indexService.index())

        return "index"
    }
}
