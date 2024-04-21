package com.damjancoric.template

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.stream.createHTML

fun htmlBaseTemplate(bodyFn: BODY.() -> Unit): String {
    return createHTML().html {
        head {
//            script { src = "/htmx.org/$htmxVersion/dist/htmx.js" }
            link(rel = "stylesheet", href = "/static/tailwind/styles.css", type = "text/css")
        }
        body {
            bodyFn()
        }
    }
}