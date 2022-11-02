package me.camilo.jaimes.application.services

import Author
import me.camilo.jaimes.application.db.DatabaseFactory
import me.camilo.jaimes.application.db.tables.AuthorTable
import me.camilo.jaimes.application.db.tables.CategoryTable
import me.camilo.jaimes.application.db.tables.VideoTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement

class AuthorServiceImpl : AuthorService {
    override suspend fun getAllAuthors(): List<Author>? {
        val authors = DatabaseFactory.dbQuery {
            AuthorTable.selectAll()
                .mapNotNull { rowToAuthor(it) }
        }
        return authors
    }

    override suspend fun addAuthor(nombre: String): Author? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = AuthorTable.insert {
                it[AuthorTable.fullName] = nombre
            }
        }
        return rowToAuthor(statement?.resultedValues?.get(0))
    }

    override suspend fun getAuthorByIdVideo(id: Int): Author? {
        val author = DatabaseFactory.dbQuery {
            AuthorTable.select {
                AuthorTable.id.eqSubQuery(
                    VideoTable.slice( VideoTable.authorId )
                        .select {
                                VideoTable.id.eq(id)
                        }
                    )
                }.map { rowToAuthor(it) }.singleOrNull()
            }
        return author
    }

    private fun rowToAuthor(row: ResultRow?): Author? {
        return if(row == null) null
        else
            Author (
                id = row[AuthorTable.id],
                fullName = row[AuthorTable.fullName]
            )
    }
}