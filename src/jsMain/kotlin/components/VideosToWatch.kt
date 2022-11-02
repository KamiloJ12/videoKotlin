package components


import Video
import csstype.ClassName
import csstype.px
import csstype.rgba
import emotion.react.css
import kotlinx.browser.document
import react.FC
import react.Props
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.i
import react.dom.html.ReactHTML.span


external interface VideoToWatchProps : Props {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

val VideosToWatch = FC<VideoToWatchProps> { props ->

    div {
        className = ClassName("card-videos-list")
        div {
            className = ClassName("header-videos-list")
            h3 {
                className = ClassName("header-title")
                +"Videos to Watch"
            }
            div {
                className = ClassName("btn-container")
                button {
                    className = ClassName("btn-addVideo")
                    onClick = {
                        document.getElementById("modal-addVideo")?.classList?.add("show")
                    }
                    i {
                        className = ClassName("bx bx-video-plus bx-sm")
                    }
                    span {
                        +"Add Video"
                    }
                }
                button {
                    className = ClassName("btn-addCategory")
                    onClick = {
                        document.getElementById("modal-addCategory")?.classList?.add("show")
                    }
                    i {
                        className = ClassName("bx bx-category bx-sm")
                    }
                    span {
                        +"Add Category"
                    }
                }
                button {
                    className = ClassName("btn-addAuthor")
                    onClick = {
                        document.getElementById("modal-addAuthor")?.classList?.add("show")
                    }
                    i {
                        className = ClassName("bx bx-user-plus bx-sm")
                    }
                    span {
                        +"Add Author"
                    }
                }
            }
        }
        div {
            className = ClassName("videos-list")
            for(video in props.videos){

                p {
                    css {
                        if(video == props.selectedVideo) {
                            backgroundColor = rgba(243, 166, 24, 0.815)
                            borderRadius = 5.px
                            padding = 10.px
                        }
                    }
                    onClick = {
                        props.onSelectVideo(video)
                    }
                    + "${video.author?.fullName} : ${video.title}"
                }
            }
        }
    }

}