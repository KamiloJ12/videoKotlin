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
import react.dom.html.ReactHTML.input
import react.useEffectOnce
import react.useState

external interface ModalAddCategoryProps: Props {
    var onSubmit: (String) -> Unit
}

val ModalAddCategory = FC<ModalAddCategoryProps> {props ->

    val (category, setCategory)  = useState("")

    val submitHandler: FormEventHandler<HTMLFormElement> = {
        it.preventDefault()
        setCategory("")
        props.onSubmit(category)
    }

    val changeHandler: ChangeEventHandler<HTMLInputElement> = {
        setCategory(it.target.value)
    }


    div {
        className = ClassName("modal")
        id = "modal-addCategory"
        div {
            className = ClassName("modal-dialog")
            div {
                className = ClassName("modal-dialog")
                div {
                    className = ClassName("modal-header")
                    h1 {
                        +"Add New Category"
                    }
                    div {
                        className = ClassName("div-btn-close")
                        button {
                            type = ButtonType.button
                            className = ClassName("btn-close")
                            onClick = {
                                document.getElementById("modal-addCategory")?.classList?.remove("show")
                            }
                            ReactHTML.i {
                                className = ClassName("bx bx-x bx-sm")
                            }
                        }
                    }

                }
                div {
                    className = ClassName("modal-body")
                    form {
                        id = "form-category"
                        onSubmit = submitHandler
                        div {
                            input {
                                type = InputType.text
                                placeholder = "Category"
                                onChange = changeHandler
                                value = category
                            }
                        }
                    }
                }
                div {
                    className = ClassName("modal-footer")
                    ReactHTML.button {
                        type = ButtonType.button
                        className = ClassName("btn btn-secondary")
                        onClick = {
                            document.getElementById("modal-addCategory")?.classList?.remove("show")
                        }
                        +"Close"
                    }
                    ReactHTML.button {
                        type = ButtonType.submit
                        className = ClassName("btn btn-primary")
                        form = "form-category"
                        +"Save changes"
                    }
                }
            }
        }
    }
}