package jp.co.casareal.kotlin.dao

import java.sql.Connection
import java.sql.Driver
import java.sql.DriverManager
import java.sql.Statement

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo

import org.seasar.doma.jdbc.NoCacheSqlFileRepository
import org.seasar.doma.jdbc.SqlFile
import org.seasar.doma.jdbc.SqlFileRepository
import org.seasar.doma.jdbc.dialect.Dialect

/**
 * 
 */
class UsersDaoTest {

    lateinit var repository: SqlFileRepository
    lateinit var dialect: Dialect
    lateinit var driver: Driver
    lateinit var url: String
    lateinit var user: String
    lateinit var password: String

    @BeforeEach
    protected fun setUp() {
        repository = NoCacheSqlFileRepository()
        dialect = org.seasar.doma.jdbc.dialect.PostgresDialect()
        url = "jdbc:postgresql://192.168.0.113:5432/kotlin"
        user = "root"
        password = "password"
    }

    protected fun execute(sqlFile: SqlFile) {
        val connection = getConnection()
        try {
            connection.setAutoCommit(false)
            val statement = connection.createStatement()
            try {
                statement.execute(sqlFile.getSql())
            } finally {
                statement.close()
            }
        } finally {
            try {
                connection.rollback()
            } finally {
                connection.close()
            }
        }
    }

    protected fun getConnection(): Connection {
        return DriverManager.getConnection(url, user, password)
    }

    @Test
    public fun testSelectById(testInfo: TestInfo) {
        val sqlFile = repository.getSqlFile(testInfo.getTestMethod().get(), "META-INF/jp/co/casareal/kotlin/dao/UsersDao/selectById.sql", dialect)
        execute(sqlFile)
    }

}
