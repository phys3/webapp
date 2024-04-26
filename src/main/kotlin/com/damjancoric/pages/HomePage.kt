package com.damjancoric.pages

import io.ktor.server.html.*
import kotlinx.html.FlowContent
import kotlinx.html.*

class HomePage : Template<FlowContent> {
    override fun FlowContent.apply() {
        div("flex w-screen h-screen justify-center items-center") {
            h1("text-teal-700") { +"Damjan" }
            div() {

                article {
                    p("text-teal-700") { +"This is a blog post" }
                }
            }
        }
    }
}