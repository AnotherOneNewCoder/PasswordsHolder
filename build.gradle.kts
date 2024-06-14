//buildscript {
//
//    dependencies {
//        //classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
//        classpath("app.cash.sqldelight:gradle-plugin:2.0.1")
//    }
//}

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.jetbrainsCompose).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.sqldelight).apply(false)

}
