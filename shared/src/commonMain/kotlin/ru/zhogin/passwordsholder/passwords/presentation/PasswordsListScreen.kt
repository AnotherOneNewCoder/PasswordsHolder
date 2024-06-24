package ru.zhogin.passwordsholder.passwords.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.zhogin.passwordsholder.core.presentation.ImagePicker
import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.presentation.components.AddPasswordSheet
import ru.zhogin.passwordsholder.passwords.presentation.components.PasswordDetailSheet
import ru.zhogin.passwordsholder.passwords.presentation.components.PasswordListItem

@Composable
fun PasswordsListScreen(
    state: PasswordListState,
    newPassword: Password?,
    onEvent: (PasswordListEvent) -> Unit,
    imagePicker: ImagePicker,
    modifier: Modifier = Modifier,
) {
    imagePicker.RegisterPicker { imageBytes ->
        onEvent(PasswordListEvent.OnPhotoClicked(imageBytes))
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(PasswordListEvent.OnAddNewPasswordClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add password"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "My passwords (${state.password.size})",
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            items(state.password) { password ->
                PasswordListItem(
                    password = password,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(PasswordListEvent.SelectPassword(password))
                        }
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
    PasswordDetailSheet(
        isOpen = state.isSelectedPasswordSheepOpen,
        selectedPassword = state.selectedPassword,
        onEvent = onEvent,
    )
    AddPasswordSheet(
        state = state,
        newPassword = newPassword,
        isOpen = state.isAddPasswordSheetOpen,
        onEvent = { event ->
            if (event is PasswordListEvent.AddPhotoClicked) {
                imagePicker.pickImage()
            }
            onEvent(event)
        },
    )
}