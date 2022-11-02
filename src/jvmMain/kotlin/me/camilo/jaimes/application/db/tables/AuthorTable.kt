package me.camilo.jaimes.application.db.tables

import me.camilo.jaimes.application.db.tables.VideoTable.clientDefault
import org.jetbrains.exposed.sql.Table

object AuthorTable: Table("author") {
    val id = integer("id").autoIncrement()
    val fullName = varchar("full_name", 256)
    val stateId = reference("state_id", StateTable.id, fkName="AUTHOR_STATE_ID_FK").clientDefault { 1 }
    override val primaryKey = PrimaryKey(id, name="AUTHOR_ID_PK")
}