package me.camilo.jaimes.application.repositories

import Video

interface VideoRepository {
    suspend fun getAllVideos(): List<Video>
    suspend fun addVideo(video: Video): Video?
    suspend fun changeWacthVideo(video: Video)
}