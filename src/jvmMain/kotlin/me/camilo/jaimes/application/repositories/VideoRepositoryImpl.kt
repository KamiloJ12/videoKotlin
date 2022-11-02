package me.camilo.jaimes.application.repositories

import Video
import me.camilo.jaimes.application.services.AuthorService
import me.camilo.jaimes.application.services.CategoryService
import me.camilo.jaimes.application.services.LanguageService
import me.camilo.jaimes.application.services.VideoService


class VideoRepositoryImpl(
    val videoService: VideoService,
    val authorService: AuthorService,
    val languageService: LanguageService,
    val categoryService: CategoryService
) : VideoRepository {
    override suspend fun getAllVideos(): List<Video> {
        var videos = videoService.getAllVideos()
        if (videos != null) {
            videos.map {
                it.author = authorService.getAuthorByIdVideo(it.id)
                it.language = languageService.getLenguageByIdVideo(it.id)
                it.categories = categoryService.getAllCategoriesByIdVideo(it.id)
            }
            return videos
        } else {
            return emptyList<Video>()
        }
    }

    override suspend fun addVideo(video: Video): Video? {
        val videoRespond = videoService.addVideo(video)
        for( category in video.categories!!){
            category.id?.let {
                if (videoRespond != null) {
                    categoryService.addCategoryByVIdeo(videoRespond.id, it)
                }
            }
        }
        return video
    }

    override suspend fun changeWacthVideo(video: Video) {
        videoService.changeWacthVideo(video)
    }
}