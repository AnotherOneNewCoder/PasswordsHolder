package ru.zhogin.passwordsholder.di

import ru.zhogin.passwordsholder.passwords.domain.PasswordDataSource

expect class AppModule {
    val passwordDataSource: PasswordDataSource
}