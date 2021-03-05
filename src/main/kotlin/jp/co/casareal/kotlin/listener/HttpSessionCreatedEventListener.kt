package jp.co.casareal.kotlin.listener

import org.apache.commons.logging.LogFactory
import org.springframework.context.ApplicationListener
import org.springframework.security.web.session.HttpSessionCreatedEvent
import org.springframework.stereotype.Component

/**
 * セッション生成時のイベントハンドラー
 */
@Component
class HttpSessionCreatedEventListener : ApplicationListener<HttpSessionCreatedEvent> {

    /** ログ */
    private val logger = LogFactory.getLog(HttpSessionCreatedEventListener::class.java)

    override fun onApplicationEvent(event: HttpSessionCreatedEvent) {
        logger.info("### HttpSessionCreatedEvent")
    }
}
