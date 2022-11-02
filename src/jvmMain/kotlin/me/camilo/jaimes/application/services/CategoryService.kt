package me.camilo.jaimes.application.services

import Category

interface CategoryService {
    suspend fun getAllCategories(): List<Category>?
    suspend fun addCategory(category: String): Category?
    suspend fun getAllCategoriesByIdVideo(id: Int): List<Category>?
    suspend fun addCategoryByVIdeo(videoId: Int, categoryId: Int)
}