package me.camilo.jaimes.application.services

import Video
import me.camilo.jaimes.application.db.DatabaseFactory
import me.camilo.jaimes.application.db.tables.AuthorTable
import me.camilo.jaimes.application.db.tables.VideoTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.update

class VideoServiceImpl : VideoService {
    override suspend fun addVideo(params: Video): Video? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = VideoTable.insert {
                it[VideoTable.title] = params.title
                it[VideoTable.watched] = params.watched
                it[VideoTable.year] = params.year
                it[VideoTable.authorId] = params.author!!.id
                it[VideoTable.languageId] = params.language!!.id
                it[VideoTable.videoUrl] = params.videoUrl
            }
        }
        return rowToVideo(statement?.resultedValues?.get(0))
    }

    override suspend fun getAllVideos(): List<Video>? {
        val videos = DatabaseFactory.dbQuery {
            VideoTable.selectAll()
                .mapNotNull { rowToVideo(it) }
        }
        return videos
    }

    override suspend fun changeWacthVideo(video: Video) {
        DatabaseFactory.dbQuery {
            VideoTable.update({
                VideoTable.id eq video.id
            }){
                it[VideoTable.watched] = video.watched
            }
        }

    }

    private fun rowToVideo(row: ResultRow?): Video? {
        return if(row == null) null
        else
            Video (
                id = row[VideoTable.id],
                title = row[VideoTable.title],
                videoUrl = row[VideoTable.videoUrl],
                year = row[VideoTable.year],
                watched = row[VideoTable.watched]
            )
    }
}