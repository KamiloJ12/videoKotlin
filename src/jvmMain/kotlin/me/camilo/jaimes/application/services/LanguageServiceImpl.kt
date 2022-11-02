package me.camilo.jaimes.application.services

import Language
import me.camilo.jaimes.application.db.DatabaseFactory
import me.camilo.jaimes.application.db.tables.AuthorTable
import me.camilo.jaimes.application.db.tables.LanguageTable
import me.camilo.jaimes.application.db.tables.VideoTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eqSubQuery
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class LanguageServiceImpl : LanguageService {
    override suspend fun getAllLanguages(): List<Language>? {
        val languages = DatabaseFactory.dbQuery {
            LanguageTable.selectAll()
                .mapNotNull { rowToLanguage(it) }
        }
        return languages
    }

    override suspend fun getLenguageByIdVideo(id: Int): Language? {
        val lenguage = DatabaseFactory.dbQuery {
            LanguageTable.select {
                LanguageTable.id.eqSubQuery(
                    VideoTable.slice( VideoTable.languageId )
                        .select {
                            VideoTable.id.eq(id)
                        }
                )
            }.map { rowToLanguage(it) }.singleOrNull()
        }
        return lenguage
    }

    private fun rowToLanguage(row: ResultRow?): Language? {
        return if(row == null) null
        else
            Language(
                id = row[LanguageTable.id],
                language = row[LanguageTable.language]
            )
    }
}