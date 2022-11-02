package me.camilo.jaimes.application.repositories

import Category
import me.camilo.jaimes.application.services.CategoryService

class CategoryRepositoryImpl(
    private val categoryService: CategoryService
) : CategoryRepository {
    override suspend fun getAllCategories(): List<Category> {
        val categories = categoryService.getAllCategories()
        if(categories != null){
            return categories
        } else {
            return emptyList<Category>()
        }
    }

    override suspend fun addCategory(category: Category): Category? {
        return categoryService.addCategory(category.category)
    }
}