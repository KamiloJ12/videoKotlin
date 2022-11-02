package me.camilo.jaimes.application.repositories

import Author
import me.camilo.jaimes.application.services.AuthorService

class AuthorRepositoryImpl (
    val authorService: AuthorService
    ) : AuthorRepository {
    override suspend fun getAllAuthors(): List<Author> {
        val authors = authorService.getAllAuthors()
        if(authors != null){
            return authors
        } else {
        return emptyList<Author>()
        }

    }

    override suspend fun addAuthor(author: Author): Author? {
        return authorService.addAuthor(author.fullName)
    }
}