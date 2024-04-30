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
        val isDevMode = this@routing.environment?.developmentMode ?: false
            if (call.request.origin.scheme == "http" && !isDevMode) {
                val httpsUrl = "https://${call.request.host()}${call.request.origin.uri}"
                call.respondRedirect(httpsUrl, permanent = true)
                return@intercept
            }
        }
        get("/projects") {

            call.respondHtmlTemplate(LayoutTemplate()) {
                content {
                        div("text-3xl") {
                            h1("underline") { +"nothing yet" }
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

