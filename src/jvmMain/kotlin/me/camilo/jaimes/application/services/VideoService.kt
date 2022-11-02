package me.camilo.jaimes.application.services

import Video

interface VideoService {
    suspend fun addVideo(params: Video): Video?
    suspend fun getAllVideos(): List<Video>?
    suspend fun changeWacthVideo(video: Video)
}