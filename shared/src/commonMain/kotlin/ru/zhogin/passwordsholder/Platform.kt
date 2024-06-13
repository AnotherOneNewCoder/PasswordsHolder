package ru.zhogin.passwordsholder

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform