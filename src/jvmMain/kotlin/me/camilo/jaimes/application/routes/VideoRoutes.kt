package me.camilo.jaimes.application.routes

import Video
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.camilo.jaimes.application.repositories.VideoRepository

fun Route.videosRouting(videoRepository: VideoRepository) {
    route("api/" + Video.path){
        get {
            val result = videoRepository.getAllVideos()
            call.respond(result)
        }
        post {
            val params = call.receive<Video>()
            videoRepository.addVideo(params)
            call.respond(HttpStatusCode.Created)
        }
        put {
            val params = call.receive<Video>()
            val result = videoRepository.addVideo(params)
            call.respond(HttpStatusCode.Created)
        }
    }
}

