package me.camilo.jaimes.application.routes

import Category
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.camilo.jaimes.application.repositories.CategoryRepository

fun Route.categoryRouting(repository: CategoryRepository) {
    route("api/" + Category.path){
        get {
            val result = repository.getAllCategories()
            call.respond(result)
        }
        post {
            val params = call.receive<Category>()
            val result = repository.addCategory(params)
            call.respond(HttpStatusCode.Created)
        }
    }
}