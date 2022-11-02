package me.camilo.jaimes.application

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer

import io.ktor.server.netty.Netty
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import me.camilo.jaimes.application.db.DatabaseFactory

import me.camilo.jaimes.application.routes.configureRouting

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 9090
    embeddedServer(Netty, port = port) {

        DatabaseFactory.init()

        install(ContentNegotiation) {
            json()
        }
        install(Compression) {
            gzip()
        }

        configureRouting()

    }.start(wait = true)
}