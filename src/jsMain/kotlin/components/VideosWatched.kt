package components

import Video
import csstype.ClassName
import csstype.px
import csstype.rgba
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.div

external interface VideosWatchedProps : Props {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

val VideosWatched = FC<VideosWatchedProps> {props ->
    div {
        className = ClassName("card-videos-list")
        div {
            className = ClassName("header-videos-list")
            h3 {
                className = ClassName("header-title")
                +"Videos Watched"
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