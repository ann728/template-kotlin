package jp.co.casareal.kotlin.fixtures

import com.ninja_squad.dbsetup_kotlin.DbSetupBuilder
import com.ninja_squad.dbsetup_kotlin.mappedValues

data class IndexFixture (
    val id: Int = 0, 
    val name: String = "" 
)

fun DbSetupBuilder.insertIndexFixture(f: IndexFixture) {
    insertInto("index") {
        mappedValues(
                "id" to f.id,
                "name" to f.name
        )
    }
}