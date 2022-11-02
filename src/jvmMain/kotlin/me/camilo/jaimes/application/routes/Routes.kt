package me.camilo.jaimes.application.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.camilo.jaimes.application.repositories.*
import me.camilo.jaimes.application.services.*

fun Application.configureRouting() {

    val languageService: LanguageService = LanguageServiceImpl()
    val languageRepository: LanguageRepository = LanguageRepositoryImpl(languageService)

    val categoryService: CategoryService = CategoryServiceImpl()
    val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryService)

    val authorService: AuthorService = AuthorServiceImpl()
    val authorRepository: AuthorRepository = AuthorRepositoryImpl(authorService)

    val videoService: VideoService = VideoServiceImpl()
    val videoRepository: VideoRepository = VideoRepositoryImpl(videoService, authorService, languageService, categoryService)

    routing {
        get("/") {
            call.respondText(
                this::class.java.classLoader.getResource("index.html")!!.readText(),
                ContentType.Text.Html
            )
        }
        static("/static") {
            resources()
        }

        videosRouting(videoRepository)
        languageRouting(languageRepository)
        categoryRouting(categoryRepository)
        authorRouting(authorRepository)
    }
}