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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.zhogin.passwordsholder.core.presentation.ImagePicker
import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.presentation.components.AddPasswordSheet
import ru.zhogin.passwordsholder.passwords.presentation.components.AppSearchBar
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

    var searchText by rememberSaveable {
        mutableStateOf("")
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
        },
        topBar = {
            AppSearchBar(
                modifier = modifier,
                onSearchPassword = {
                    searchText = it
                }
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (searchText.isEmpty()) {
                item {
                    Text(
                        text = "My passwords (${state.password.size})",
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
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
            } else {
                val result = ArrayList<Password>()
                result.clear()
                for (password in state.password) {
                    if (password.name.lowercase().contains(searchText.lowercase())) {
                        result.add(password)
                    }
                    else if (password.login.lowercase().contains(searchText.lowercase())) {
                        result.add(password)
                    }
                }
                item {
                    Text(
                        text = "Found passwords (${result.size})",
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
                items(result) { password ->
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