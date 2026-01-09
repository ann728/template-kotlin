package jp.co.casareal.kotlin.daoimpl

import com.ninja_squad.dbsetup.destination.DriverManagerDestination
import com.ninja_squad.dbsetup_kotlin.dbSetup
import jp.co.casareal.kotlin.fixtures.UsersFixture
import jp.co.casareal.kotlin.fixtures.insertUsersFixture
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
class UsersDaoImplTest {

    @Autowired
    var usersDao: UsersDao? = null
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
            deleteAllFrom(listOf("users"))
            insertUsersFixture(UsersFixture(login_user = "user", password = "pass1", name = "user", role_cd = "01"))
            insertUsersFixture(UsersFixture(login_user = "admin", password = "pass2", name = "admin", role_cd = "00"))
        }.launch()
    }

    @Test
    public fun testSelectById(testInfo: TestInfo) {
        val users1 = usersDao?.selectById("user",)
        assertAll("index assertions1",
            { Assertions.assertEquals("user", users1?.loginUser) },
            { Assertions.assertEquals("pass1", users1?.password) },
            { Assertions.assertEquals("user", users1?.name) },
            { Assertions.assertEquals("01", users1?.roleCd) }
        )

        val users2 = usersDao?.selectById("admin",)
        assertAll("index assertions2",
            { Assertions.assertEquals("admin", users2?.loginUser) },
            { Assertions.assertEquals("pass2", users2?.password) },
            { Assertions.assertEquals("admin", users2?.name) },
            { Assertions.assertEquals("00", users2?.roleCd) }
        )
    }
}
