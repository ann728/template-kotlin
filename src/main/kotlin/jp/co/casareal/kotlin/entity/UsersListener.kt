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
class UsersListener : EntityListener<Users> {

    override fun preInsert(entity: Users, context: PreInsertContext<Users>) {
    }

    override fun preUpdate(entity: Users, context: PreUpdateContext<Users>) {
    }

    override fun preDelete(entity: Users, context: PreDeleteContext<Users>) {
    }

    override fun postInsert(entity: Users, context: PostInsertContext<Users>) {
    }

    override fun postUpdate(entity: Users, context: PostUpdateContext<Users>) {
    }

    override fun postDelete(entity: Users, context: PostDeleteContext<Users>) {
    }
}
