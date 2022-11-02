package components

import ReactPlayer
import Video
import csstype.AlignItems
import csstype.ClassName
import csstype.Display
import csstype.JustifyContent
import emotion.react.css
import kotlinx.browser.window
import org.w3c.dom.HTMLInputElement
import react.FC
import react.Props
import react.dom.events.ChangeEventHandler
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.ul

external interface VideoInfoProps : Props {
    var selectedVideo: Video?
    var onWatchedButtonPressed: (Video) -> Unit
}

val VideoInfo = FC<VideoInfoProps> {props ->

    val changeHandler: ChangeEventHandler<HTMLInputElement> = {
        props.selectedVideo!!.watched = it.target.checked
        props.onWatchedButtonPressed(props.selectedVideo!!)
    }

    div {
        className = ClassName("video-container")

        ReactPlayer {
            url = props.selectedVideo?.videoUrl.toString()
            controls = true
        }

        div {
            className = ClassName("checkbox-container")

            p {
                +"Watched Video?"
            }
            input {
                type = InputType.checkbox
                checked = props.selectedVideo?.watched
                onChange = changeHandler
            }
        }

        h3 {
            className = ClassName("video-title")
            +"${props.selectedVideo?.title}"
        }
        div{
            className = ClassName("video-info")
            div {
                +"Author: ${props.selectedVideo?.author?.fullName}"
            }
            div {
                +"Language: ${props.selectedVideo?.language?.language}"
            }
        }
        div {
            className = ClassName("video-list-container")
            ul {
                className = ClassName("video-list")
                for(category in props.selectedVideo?.categories!!){
                    li {
                        className = ClassName("video-list-item")
                        +"${category.category}"
                    }
                }
            }
        }
    }
}