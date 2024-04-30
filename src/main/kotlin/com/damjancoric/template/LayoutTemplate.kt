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
            link(rel = "stylesheet", href = "/static/tailwind/filter.css", type = "text/css")
            link(rel="preconnect", href="https://fonts.googleapis.com")
            link(rel="preconnect", href="https://fonts.gstatic.com")
            link(href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital,wght@0,100..700;1,100..700&display=swap", rel="stylesheet")
        }
        body() {
            insert(content)
        }
    }
}