package jp.co.casareal.kotlin.entity

import org.seasar.doma.*

@Entity(immutable = true)
@Table(name = "index")
data class Index(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    val name: String
)
