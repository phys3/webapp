package com.damjancoric.pages

import io.ktor.server.html.*
import kotlinx.html.FlowContent
import kotlinx.html.*
import java.io.File

class HomePage : Template<FlowContent> {
    val svgContent = this::class.java.classLoader.getResource("files/in.svg").readText()
    val ghLogo = this::class.java.classLoader.getResource("files/gh.svg").readText()
    override fun FlowContent.apply() {
        div("flex w-screen h-screen justify-center items-center gradient-filter") {
            a(href = "https://github.com/phys3", target = "_blank", classes = "absolute bottom-3 right-14") {
                unsafe {
                    +ghLogo
                }
            }
            a(href = "https://www.linkedin.com/in/damjan-%C4%8Dori%C4%87-a4116a198/", target = "_blank", classes = "absolute bottom-3 right-4") {
                unsafe {
                    +svgContent
                }
            }
        }
        h1("josefin-sans text-7xl text-red-600 absolute left-3 top-3") { +"Damjan" }
        a(href = "/projects", classes = "absolute bottom-1 right-24") {
            p("josefin-sans text-4xl projects") { +"projects" }
        }
    }
}