package jp.co.casareal.kotlin.listener

import org.apache.commons.logging.LogFactory
import org.springframework.context.ApplicationListener
import org.springframework.security.web.session.HttpSessionDestroyedEvent
import org.springframework.stereotype.Component

/**
 * セッション破棄時のイベントハンドラー
 */
@Component
class HttpSessionDestroyedEventListener : ApplicationListener<HttpSessionDestroyedEvent> {

    /** ログ */
    private val logger = LogFactory.getLog(HttpSessionDestroyedEventListener::class.java)

    override fun onApplicationEvent(event: HttpSessionDestroyedEvent) {
        logger.info("### HttpSessionDestroyedEvent")
    }
}
