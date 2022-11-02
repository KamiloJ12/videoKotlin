package me.camilo.jaimes.application.repositories

import Category

interface CategoryRepository {
    suspend fun getAllCategories(): List<Category>
    suspend fun addCategory(category: Category): Category?
}