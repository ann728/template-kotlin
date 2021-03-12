package jp.co.casareal.kotlin.entity

import org.seasar.doma.jdbc.entity.EntityListener
import org.seasar.doma.jdbc.entity.PostDeleteContext
import org.seasar.doma.jdbc.entity.PostInsertContext
import org.seasar.doma.jdbc.entity.PostUpdateContext
import org.seasar.doma.jdbc.entity.PreDeleteContext
import org.seasar.doma.jdbc.entity.PreInsertContext
import org.seasar.doma.jdbc.entity.PreUpdateContext

/**
 * 
 */
class IndexListener : EntityListener<Index> {

    override fun preInsert(entity: Index, context: PreInsertContext<Index>) {
    }

    override fun preUpdate(entity: Index, context: PreUpdateContext<Index>) {
    }

    override fun preDelete(entity: Index, context: PreDeleteContext<Index>) {
    }

    override fun postInsert(entity: Index, context: PostInsertContext<Index>) {
    }

    override fun postUpdate(entity: Index, context: PostUpdateContext<Index>) {
    }

    override fun postDelete(entity: Index, context: PostDeleteContext<Index>) {
    }
}
