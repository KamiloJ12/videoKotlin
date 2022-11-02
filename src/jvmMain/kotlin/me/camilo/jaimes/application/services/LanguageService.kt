package me.camilo.jaimes.application.services

import Language

interface LanguageService {
    suspend fun getAllLanguages(): List<Language>?
    suspend fun getLenguageByIdVideo(id: Int): Language?
}