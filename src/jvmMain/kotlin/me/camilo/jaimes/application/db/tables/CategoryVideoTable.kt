package me.camilo.jaimes.application.db.tables

import org.jetbrains.exposed.sql.Table

object CategoryVideoTable: Table("category_video") {
    val categoryId = reference("category_id", CategoryTable.id, fkName="CATEGORY_VIDEO_CATEGORY_ID_FK")
    val videoId = reference("video_id", VideoTable.id, fkName="CATEGORY_VIDEO_VIDEO_ID_FK")
    override val primaryKey = PrimaryKey(categoryId, videoId, name="CATEGORY_VIDEO_CAT_ID_VIDO_ID_PK")
}