package com.damjancoric.plugins

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.configureTemplating() {
    routing {
        get("/html-dsl") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/static/tailwind/styles.css", type = "text/css")
                }
                body {
                    h1 { +"HTML" }
                    div("text-2xl") {
                        h1("underline") { +"title" }

                        article {
                            p { +"This is a blog post" }
                        }
                    }
                }
            }
        }
    }
}
