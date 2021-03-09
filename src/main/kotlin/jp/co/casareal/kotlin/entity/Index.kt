package jp.co.casareal.kotlin.entity

import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.GeneratedValue
import org.seasar.doma.GenerationType
import org.seasar.doma.Id
import org.seasar.doma.Metamodel
import org.seasar.doma.Table

/**
 */
@Entity(listener = IndexListener::class, metamodel = Metamodel())
@Table(name = "index")
class Index : AbstractIndex() {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = -1

    /** */
    @Column(name = "name")
    var name: String? = null
}
