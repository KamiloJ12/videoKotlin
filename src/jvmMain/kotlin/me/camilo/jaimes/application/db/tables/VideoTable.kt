package me.camilo.jaimes.application.db.tables

import me.camilo.jaimes.application.db.tables.CategoryTable.autoIncrement
import org.jetbrains.exposed.sql.Table

object VideoTable: Table("video") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 256)
    val authorId = reference("author_id", AuthorTable.id, fkName="VIDEO_AUTHOR_ID_FK")
    val videoUrl = varchar("video_url", 256)
    val year  = varchar("year", 256)
    val watched = bool("watched").clientDefault { false }
    val languageId = reference("language_id", LanguageTable.id, fkName="VIDEO_LANGUAGE_ID_FK")
    val stateId = reference("state_id", StateTable.id, fkName="VIDEO_STATE_ID_FK").clientDefault { 1 }
    override val primaryKey = PrimaryKey(id, name="VIDEO_ID_PK")
}