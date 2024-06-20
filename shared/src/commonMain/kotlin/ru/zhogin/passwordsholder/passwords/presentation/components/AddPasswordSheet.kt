package ru.zhogin.passwordsholder.passwords.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.presentation.PasswordListEvent
import ru.zhogin.passwordsholder.passwords.presentation.PasswordListState

@Composable
fun AddPasswordSheet(
    state: PasswordListState,
    newPassword: Password?,
    isOpen: Boolean,
    onEvent: (PasswordListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    CustomBottomSheet(
        visible = isOpen,
        modifier = modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(60.dp))
                if (newPassword?.photoBytes == null) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(40))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .clickable {
                                onEvent(PasswordListEvent.AddPhotoClicked)
                            }
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                shape = RoundedCornerShape(40)
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add photo",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                } else {
                    PasswordPhoto(
                        password = newPassword,
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                onEvent(PasswordListEvent.AddPhotoClicked)
                            }
                    )
                }
                Spacer(Modifier.height(16.dp))
                PasswordTextField(
                    value = newPassword?.name ?: "",
                    placeholder = "Title",
                    error = state.validationNameError,
                    onValueChanged = { onEvent(PasswordListEvent.OnNameChanged(it))},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                PasswordTextField(
                    value = newPassword?.login ?: "",
                    placeholder = "Login",
                    error = state.validationLoginError,
                    onValueChanged = { onEvent(PasswordListEvent.OnLoginChanged(it))},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                PasswordTextField(
                    value = newPassword?.pass ?: "",
                    placeholder = "Password",
                    error = state.validationPassError,
                    onValueChanged = { onEvent(PasswordListEvent.OnPassChanged(it))},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        onEvent(PasswordListEvent.SavePassword)
                    }
                ) {
                    Text(text = "Save")
                }

            }
            IconButton(
                onClick = {
                    onEvent(PasswordListEvent.DismissPassword)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Dismiss"
                )
            }
        }
    }
}

@Composable
private fun PasswordTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}