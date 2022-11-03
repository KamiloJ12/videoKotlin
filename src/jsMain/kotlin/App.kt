

import components.*
import csstype.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.useEffectOnce
import react.useState

private val scope = MainScope()

val App = FC<Props> {

    var videoList by useState(emptyList<Video>())
    var authorList by useState(emptyList<Author>())
    var languageList by useState(emptyList<Language>())
    var categoryList by useState(emptyList<Category>())
    var currentVideo: Video? by useState(null)

    useEffectOnce {
        scope.launch {
            videoList = getVideos()
            authorList = getAuthors()
            languageList = getLanguages()
            categoryList = getCategories()
        }
    }

    val unwatchedVideos = videoList.filter { !it.watched }
    val watchedVideos = videoList.filter { it.watched }

    div {
        h1 {
            className = ClassName("title")
            +"VideosKotlin!"
        }

        currentVideo?.let { curr ->
            VideoInfo {
                selectedVideo = curr
                onWatchedButtonPressed = { video ->
                    scope.launch {
                        updateWatchVideo(video)
                        videoList = getVideos()
                    }
                }
            }
        }

        div {
            className = ClassName("videos-container")
            VideosToWatch {
                videos = unwatchedVideos
                selectedVideo = currentVideo
                onSelectVideo = { video ->
                    currentVideo = video
                }
            }

            VideosWatched {
                videos = watchedVideos
                selectedVideo = currentVideo
                onSelectVideo = { video ->
                    currentVideo = video
                }
            }
        }
    }

    ModalAddVideo {
        languages = languageList
        authors = authorList
        categories =  categoryList
        onSubmit = { video ->
            scope.launch {
                addVideo(video)
                videoList = getVideos()
            }
        }
    }
    ModalAddAuthor {
        onSubmit = { input ->
            val author: Author = Author(id=0, fullName=input)
            scope.launch {
                addAuthor(author)
                authorList = getAuthors()
            }
        }
    }
    ModalAddCategory {
        onSubmit = { input ->
            val category: Category = Category(id=0, category=input)
            scope.launch {
                addCategory(category)
                categoryList = getCategories()
            }
        }
    }
}