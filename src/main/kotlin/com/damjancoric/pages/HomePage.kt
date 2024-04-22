package com.damjancoric.pages

import io.ktor.server.html.*
import kotlinx.html.FlowContent
import kotlinx.html.*

class HomePage : Template<FlowContent> {
    override fun FlowContent.apply() {
        h1 { +"HTML" }
        div("text-3xl") {
            h1("underline") { +"title" }

            article {
                p { +"This is a blog post" }
            }
        }
    }
}