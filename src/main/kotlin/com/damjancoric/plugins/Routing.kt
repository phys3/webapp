package com.damjancoric.plugins

import com.damjancoric.template.htmlBaseTemplate
import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.webjars.*
import kotlinx.html.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    install(Resources)
    routing {
        get("/case-study") {
            call.respondHtml {
                htmlBaseTemplate {
                    h1 { +"HTML" }
                    div("text-3xl") {
                        h1("underline") { +"title" }

                        article {
                            p { +"This is a blog post" }
                        }
                    }
                }
            }
        }
        get("/") {
            call.respondHtml {
                head {
                    script { src = "static/htmx.min.js" }
                    link(rel = "stylesheet", href = "/static/tailwind/styles.css", type = "text/css")
                }
                body {
                    h1 { +"HTML" }
                    div("text-3xl") {
                        h1("underline") { +"title" }

                        article {
                            p { +"This is a blog post" }
                        }
                    }
                }
            }
        }
        staticResources("/static", "files")
    }
}

