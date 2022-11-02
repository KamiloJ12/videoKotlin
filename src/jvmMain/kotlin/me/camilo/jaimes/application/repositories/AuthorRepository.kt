package me.camilo.jaimes.application.repositories

import Author

interface AuthorRepository {
    suspend fun getAllAuthors(): List<Author>
    suspend fun  addAuthor(author: Author): Author?
}