package jp.co.casareal.kotlin.config

import jp.co.casareal.kotlin.dao.EmployeeDao
import jp.co.casareal.kotlin.dao.EmployeeDaoImpl
import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.dialect.Dialect
import org.seasar.doma.jdbc.dialect.PostgresDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DomaConfig {
    @Bean
    fun config(dataSource: DataSource, dialect: Dialect): Config {
        return object : Config {
            override fun getDataSource(): DataSource = dataSource
            override fun getDialect(): Dialect = dialect
        }
    }

    @Bean
    fun dialect(): Dialect {
        return PostgresDialect()
    }
    @Bean
    fun employeeDao(config: Config): EmployeeDao {
        return EmployeeDaoImpl(config)
    }
}