package jp.co.casareal.kotlin.daoimpl

import com.ninja_squad.dbsetup.destination.DriverManagerDestination
import com.ninja_squad.dbsetup_kotlin.dbSetup
import jp.co.casareal.kotlin.fixtures.IndexFixture
import jp.co.casareal.kotlin.fixtures.insertIndexFixture
import org.junit.jupiter.api.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

/**
 * 
 */
@ActiveProfiles("unit")
@SpringBootTest
class IndexDaoImplTest {

    @Autowired
    var indexDao: IndexDao? = null
    @Value("\${spring.datasource.url}")
    val url = ""
    @Value("\${spring.datasource.username}")
    val user = ""
    @Value("\${spring.datasource.password}")
    val password = ""


    @BeforeEach
    protected fun setUp() {
        val dest = DriverManagerDestination(url, user, password)
        dbSetup(dest) {
            deleteAllFrom(listOf("index"))
            insertIndexFixture(IndexFixture(id = 1, name = "user1"))
            insertIndexFixture(IndexFixture(id = 2, name = "user2"))
        }.launch()
    }

    @Test
    public fun testSelectById(testInfo: TestInfo) {
        val index1 = indexDao?.selectById(1)
        assertAll("index assertions1",
            { Assertions.assertEquals(1, index1?.id) },
            { Assertions.assertEquals("user1", index1?.name) }
        )
        val index2 = indexDao?.selectById(2)
        assertAll("index assertions2",
            { Assertions.assertEquals(2, index2?.id) },
            { Assertions.assertEquals("user2", index2?.name) }
        )
    }

}
