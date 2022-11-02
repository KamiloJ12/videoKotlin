package components

import csstype.ClassName
import kotlinx.browser.document
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import react.FC
import react.Props
import react.dom.events.ChangeEventHandler
import react.dom.events.FormEventHandler
import react.dom.html.ButtonType
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.i
import react.dom.html.ReactHTML.input
import react.useEffectOnce
import react.useState

external interface ModalAddAuthorProps: Props {
    var onSubmit: (String) -> Unit
}

val ModalAddAuthor = FC<ModalAddAuthorProps> {props ->

    val (authorName, setAuthorName)  = useState("")

    val submitHandler: FormEventHandler<HTMLFormElement> = {
        it.preventDefault()
        setAuthorName("")
        props.onSubmit(authorName)
    }

    val changeHandler: ChangeEventHandler<HTMLInputElement> = {
        setAuthorName(it.target.value)
    }

    div {
        className = ClassName("modal")
        id = "modal-addAuthor"
        div {
            className = ClassName("modal-dialog")
            div {
                className = ClassName("modal-dialog")
                div {
                    className = ClassName("modal-header")
                    h1 {
                        +"Add New Author"
                    }
                    div {
                        className = ClassName("div-btn-close")
                        button {
                            type = ButtonType.button
                            className = ClassName("btn-close")
                            onClick = {
                                document.getElementById("modal-addAuthor")?.classList?.remove("show")
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
                        id = "form-author"
                        onSubmit = submitHandler
                        div {
                            input {
                                type = InputType.text
                                placeholder = "Author FullName"
                                onChange = changeHandler
                                value = authorName
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
                            document.getElementById("modal-addAuthor")?.classList?.remove("show")
                        }
                        +"Close"
                    }
                    button {
                        type = ButtonType.submit
                        form = "form-author"
                        className = ClassName("btn btn-primary")
                        +"Save changes"
                    }
                }
            }
        }
    }
}