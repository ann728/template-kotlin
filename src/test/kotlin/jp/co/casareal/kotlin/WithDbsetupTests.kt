package jp.co.casareal.kotlin

import com.ninja_squad.dbsetup.destination.DriverManagerDestination
import com.ninja_squad.dbsetup_kotlin.dbSetup
import jp.co.casareal.kotlin.fixtures.IndexFixture
import jp.co.casareal.kotlin.fixtures.insertIndexFixture
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext


@ActiveProfiles("dev")
@SpringBootTest
class WithDbsetupTests (){

    @Autowired
    var context: ApplicationContext? = null

    @Value("\${spring.datasource.url}")
    val url = ""
    @Value("\${spring.datasource.username}")
    val user = ""
    @Value("\${spring.datasource.password}")
    val password = ""
    val dest = DriverManagerDestination(url, user, password)

    init {
        Class.forName("org.postgresql.Driver")
    }

    @Test
    fun testInsertIndex() {
        dbSetup(dest) {
            deleteAllFrom(listOf("index"))
            insertIndexFixture(IndexFixture(id = 1, name = "user1"))
            insertIndexFixture(IndexFixture(id = 2, name = "user2"))
        }.launch()
        val stmt = dest.connection.createStatement()
        val rs = stmt.executeQuery("select * from index order by id asc")

        Assertions.assertTrue(rs.next())
        assertAll("index assertions1",
            { Assertions.assertEquals(1, rs.getInt("id")) },
            { Assertions.assertEquals("user1", rs.getString("name")) }
        )

        Assertions.assertTrue(rs.next())
        assertAll("index assertions2",
            { Assertions.assertEquals(2, rs.getInt("id")) },
            { Assertions.assertEquals("user2", rs.getString("name")) }
        )
    }
}