package me.camilo.jaimes.application.repositories

import Language
import me.camilo.jaimes.application.services.LanguageService

class LanguageRepositoryImpl(
    private val languageService: LanguageService
) : LanguageRepository {
    override suspend fun getAllLanguages(): List<Language> {
        val languages = languageService.getAllLanguages()
        if( languages != null){
            return languages
        } else {
            return emptyList<Language>()
        }
    }
}