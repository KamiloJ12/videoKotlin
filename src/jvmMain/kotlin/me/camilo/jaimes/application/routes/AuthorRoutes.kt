package me.camilo.jaimes.application.routes

import Author
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.camilo.jaimes.application.repositories.AuthorRepository

fun Route.authorRouting(authorRepository: AuthorRepository) {
    route("api/" + Author.path){
        get {
            val result = authorRepository.getAllAuthors()
            call.respond(result)
        }
        post {
            val params = call.receive<Author>()
            val result = authorRepository.addAuthor(params)
            call.respond(HttpStatusCode.Created)
        }
    }
}