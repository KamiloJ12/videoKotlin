package me.camilo.jaimes.application.db.tables

import org.jetbrains.exposed.sql.Table

object StateTable: Table("state") {
    val id = integer("id").autoIncrement()
    val state = varchar("state", 256)
    override val primaryKey = PrimaryKey(id, name="STATE_ID_PK")
}