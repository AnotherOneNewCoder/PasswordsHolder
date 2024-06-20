package ru.zhogin.passwordsholder.core.presentation
// commented to hide errors in windows
import androidx.compose.ui.window.ComposeUIViewController
import ru.zhogin.passwordsholder.App
import ru.zhogin.passwordsholder.di.AppModule

//fun MainViewController() = ComposeUIViewController {
//    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
//            UIUserInterfaceStyle.UIUserInterfaceStyleDark
//    App(
//        darkTheme = isDarkTheme,
//        dynamicColor = false,
//        appModule = AppModule(),
//        imagePicker = ImagePickerFactory(LocalUIViewController.current).createPicker(
//    )
//}