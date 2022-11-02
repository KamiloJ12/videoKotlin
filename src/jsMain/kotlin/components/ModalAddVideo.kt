package components


import Author
import Language
import Category
import Video
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import csstype.ClassName
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.*
import react.dom.events.ChangeEventHandler
import react.dom.events.FormEventHandler
import react.dom.html.ButtonType
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.i
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.useState

external interface ModalAddVideoProps : Props {
    var languages: List<Language>
    var categories: List<Category>
    var authors: List<Author>
    var onSubmit: (Video) -> Unit
}

val ModalAddVideo = FC<ModalAddVideoProps> {props ->

    val (author, setAuthor)  = useState("")
    val (language, setLanguage)  = useState("")
    val (categories, setCategories) = useState(emptyList<String>())
    val (title, setTitle)  = useState("")
    val (videoUrl, setVideoUrl)  = useState("")
    val (year, setYear)  = useState("")

    val submitHandler: FormEventHandler<HTMLFormElement> = { it ->
        it.preventDefault()
        println("Author: " + author)
        println("Language: " + language)
        println("Categories: " + categories)
        println("title: " + title)
        println("videoURL: " + videoUrl)
        println("year: " + year)

        val languageSelect = props.languages.find{ l -> l.id == language.toInt() }
        val authorSelect = props.authors.find{ a -> a.id == author.toInt() }
        val categoriesSelect = props.categories.filter{ c -> categories.contains( c.id.toString() ) }

        props.onSubmit(
            Video (
                id = 0,
                title = title,
                author = authorSelect,
                videoUrl = videoUrl,
                year = year,
                watched = false,
                language = languageSelect,
                categories = categoriesSelect
            )
        )
    }

    val changeHandlerCategories: ChangeEventHandler<HTMLSelectElement> = {
        var categoriesSelect = mutableListOf<String>()
        for( num in 0..(it.target.options.length-1)){
            val opt: HTMLOptionElement = it.target.options[num] as HTMLOptionElement
            if(opt.selected){
                categoriesSelect.add(opt.value)
            }
        }
        setCategories(categoriesSelect)
    }

    val changeHandlerAuthor: ChangeEventHandler<HTMLSelectElement> = {
        setAuthor(it.target.value)
    }

    val changeHandlerLanguage: ChangeEventHandler<HTMLSelectElement> = {
        setLanguage(it.target.value)
    }

    val changeHandlerTitle: ChangeEventHandler<HTMLInputElement> = {
        setTitle(it.target.value)
    }

    val changeHandlerVideoUrl: ChangeEventHandler<HTMLInputElement> = {
        setVideoUrl(it.target.value)
    }

    val changeHandlerYear: ChangeEventHandler<HTMLInputElement> = {
        setYear(it.target.value)
    }

    div {
        className = ClassName("modal")
        id = "modal-addVideo"
        div {
            className = ClassName("modal-dialog")
            div {
                className = ClassName("modal-dialog")
                div {
                    className = ClassName("modal-header")
                    h1 {
                        +"Add New Video"
                    }
                    div {
                        className = ClassName("div-btn-close")
                        button {
                            type = ButtonType.button
                            className = ClassName("btn-close")
                            onClick = {
                                document.getElementById("modal-addVideo")?.classList?.remove("show")
                            }
                            i {
                                className = ClassName("bx bx-x bx-sm")
                            }
                        }
                    }

                }
                div {
                    className = ClassName("modal-body")
                    form {
                        onSubmit = submitHandler
                        id = "form-video"
                        div {
                            input {
                                id = "title"
                                type = InputType.text
                                placeholder = "Titulo del video"
                                onChange = changeHandlerTitle
                                value = title
                            }
                            input {
                                id = "videoUrl"
                                type = InputType.url
                                placeholder = "Video URL"
                                onChange = changeHandlerVideoUrl
                                value = videoUrl
                            }
                            input {
                                id = "year"
                                type = InputType.number
                                placeholder = "Year"
                                onChange = changeHandlerYear
                                value = year
                            }
                        }
                        div {
                            className = ClassName("div-select")
                            select {
                                id = "author"
                                onChange = changeHandlerAuthor
                                value = author
                                for(authorProp in props.authors){

                                    option {
                                        value = authorProp.id.toString()
                                        +"${authorProp.fullName}"
                                    }
                                }
                            }
                            select {
                                id = "language"
                                onChange = changeHandlerLanguage
                                value = language
                                for(languageProp in props.languages){
                                    option {
                                        value = languageProp.id.toString()
                                        +"${languageProp.language}"
                                    }
                                }
                            }
                            select {
                                id = "categories"
                                multiple = true
                                for(categoryProp in props.categories){
                                    option {
                                        value = categoryProp.id.toString()
                                        +"${categoryProp.category}"
                                    }
                                }
                                onChange = changeHandlerCategories
                            }
                        }
                    }
                }
                div {
                    className = ClassName("modal-footer")
                    button {
                        type = ButtonType.button
                        className = ClassName("btn btn-secondary")
                        onClick = {
                            document.getElementById("modal-addVideo")?.classList?.remove("show")
                        }
                        +"Close"
                    }
                    button {
                        type = ButtonType.submit
                        className = ClassName("btn btn-primary")
                        form = "form-video"
                        +"Save changes"
                    }
                }
            }
        }
    }
}
