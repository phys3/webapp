package com.damjancoric.plugins

import com.damjancoric.pages.HomePage
import com.damjancoric.template.LayoutTemplate
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.article
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.p

fun Application.configureRouting() {
    install(Resources)
    routing {
        intercept(ApplicationCallPipeline.Plugins) {
            if (call.request.origin.scheme == "http") {
                val httpsUrl = "https://${call.request.host()}${call.request.origin.uri}"
                println(httpsUrl)
                call.respondRedirect(httpsUrl, permanent = true)
                return@intercept
            }
        }
        get("/case-study") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                content {
                        h1 { +"HTML" }
                        div("text-3xl") {
                            h1("underline") { +"case-study" }

                            article {
                                p { +"This is a blog post" }
                            }
                    }
                }
            }
        }
        get("/") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                content { insert(HomePage()){} }
            }
        }
        staticResources("/static", "files")
    }
}

