package com.damjancoric

import com.damjancoric.plugins.*
import io.ktor.server.application.*
import io.ktor.server.plugins.forwardedheaders.*
import io.ktor.server.plugins.hsts.*
import io.ktor.server.plugins.httpsredirect.*
import kotlin.time.toDuration

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
const val twoYearsInDays = 750
fun Application.module() {
    install(HSTS) {
        maxAgeDuration = twoYearsInDays.toDuration(kotlin.time.DurationUnit.DAYS)
        includeSubDomains = true
        preload = true
    }
    install(XForwardedHeaders)
    configureRouting()
}