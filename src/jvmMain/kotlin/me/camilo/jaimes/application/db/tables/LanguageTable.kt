package me.camilo.jaimes.application.db.tables

import me.camilo.jaimes.application.db.tables.VideoTable.clientDefault
import org.jetbrains.exposed.sql.Table

object LanguageTable: Table("language") {
    val id = integer("id").autoIncrement()
    val language = varchar("language", 256)
    val stateId = reference("state_id", StateTable.id, fkName="LANGUAGE_STATE_ID_FK").clientDefault { 1 }
    override val primaryKey = PrimaryKey(id, name="LANGUAGE_ID_PK")
}