package me.camilo.jaimes.application.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.camilo.jaimes.application.db.tables.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(
                AuthorTable,
                CategoryTable,
                CategoryVideoTable,
                LanguageTable,
                StateTable,
                VideoTable
                )
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = "jdbc:postgresql://ec2-44-199-9-102.compute-1.amazonaws.com:5432/dc0evg23pto9uv"
        config.username = "mvvycwqomsmkym"
        config.password = "198311ae84deb4454bc65ffc83f7214c7a41e3d844e081e5db2f9a003de76f01"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO){
        transaction {
            block()
        }
    }
}