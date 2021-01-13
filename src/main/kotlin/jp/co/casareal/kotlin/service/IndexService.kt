package jp.co.casareal.kotlin.service

import jp.co.casareal.kotlin.dao.IndexDao
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service

@Service
class IndexService(private val indexDao: IndexDao) {

    /** ログ */
    private val logger = LogFactory.getLog(IndexService::class.java)

    fun index() : String {
        logger.debug("### test2")
        return indexDao.index()
    }
}
