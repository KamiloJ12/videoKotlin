package me.camilo.jaimes.application.services

import Author

interface AuthorService {
    suspend fun getAllAuthors(): List<Author>?
    suspend fun addAuthor(nombre: String): Author?
    suspend fun getAuthorByIdVideo(id:Int): Author?
}