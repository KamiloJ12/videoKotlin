package me.camilo.jaimes.application.db.tables

import me.camilo.jaimes.application.db.tables.VideoTable.clientDefault
import org.jetbrains.exposed.sql.Table

object CategoryTable: Table("category") {
    val id = integer("id").autoIncrement()
    val category = varchar("category", 256)
    val stateId = reference("state_id", StateTable.id, fkName="CATEGORY_STATE_ID_FK").clientDefault { 1 }
    override val primaryKey = PrimaryKey(id, name="CATEGORY_ID_PK")
}