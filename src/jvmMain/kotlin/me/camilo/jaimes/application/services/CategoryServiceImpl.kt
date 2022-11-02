package me.camilo.jaimes.application.services

import Category
import me.camilo.jaimes.application.db.DatabaseFactory
import me.camilo.jaimes.application.db.tables.AuthorTable
import me.camilo.jaimes.application.db.tables.CategoryTable
import me.camilo.jaimes.application.db.tables.CategoryVideoTable
import me.camilo.jaimes.application.db.tables.VideoTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eqSubQuery
import org.jetbrains.exposed.sql.statements.InsertStatement
import javax.management.Query.and

class CategoryServiceImpl : CategoryService {
    override suspend fun getAllCategories(): List<Category>? {
        val categories = DatabaseFactory.dbQuery {
            CategoryTable.selectAll()
                .mapNotNull { rowToCategory(it) }
        }
        return categories
    }

    override suspend fun addCategory(category: String): Category? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = CategoryTable.insert {
                it[CategoryTable.category] = category
            }
        }
        return rowToCategory(statement?.resultedValues?.get(0))
    }

    override suspend fun addCategoryByVIdeo(videoId: Int, categoryId: Int) {
        DatabaseFactory.dbQuery {
            CategoryVideoTable.insert {
                it[CategoryVideoTable.categoryId] = categoryId
                it[CategoryVideoTable.videoId] = videoId
            }
        }
    }

    override suspend fun getAllCategoriesByIdVideo(id: Int): List<Category>? {
        val categories = DatabaseFactory.dbQuery {
            Join(CategoryVideoTable, CategoryTable,
                onColumn = CategoryVideoTable.categoryId,
                otherColumn = CategoryTable.id,
                joinType = JoinType.INNER,
                additionalConstraint = {
                    CategoryVideoTable.videoId eq id
                }
            ).selectAll().mapNotNull { rowToCategory(it)  }

        }
        return categories
    }

    private fun rowToCategory(row: ResultRow?): Category? {
        return if(row == null) null
        else
            Category (
                id = row[CategoryTable.id],
                category = row[CategoryTable.category]
            )
    }
}

