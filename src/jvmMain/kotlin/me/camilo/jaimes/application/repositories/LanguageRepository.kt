package me.camilo.jaimes.application.repositories

import Language

interface LanguageRepository {
    suspend fun getAllLanguages(): List<Language>
}