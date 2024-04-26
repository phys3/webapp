package com.damjancoric.template

import io.ktor.server.html.*
import kotlinx.html.*

class LayoutTemplate: Template<HTML> {
    val content = Placeholder<FlowContent>()
    override fun HTML.apply() {
        lang="en"
        head {
            meta(name = "viewport", "width=device-width, initial-scale=1")
            meta(name = "description", "personal webapp")
            script { src = "static/htmx.min.js" }
            link(rel = "stylesheet", href = "/static/tailwind/styles.css", type = "text/css")
        }
        body() {
            insert(content)
        }
    }
}