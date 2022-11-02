package me.camilo.jaimes.application.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.camilo.jaimes.application.repositories.LanguageRepository

fun Route.languageRouting(repository: LanguageRepository) {
    route("api/" + Language.path){
        get {
            val result = repository.getAllLanguages()
            call.respond(result)
        }
    }
}