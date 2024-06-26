package ru.zhogin.passwordsholder.passwords.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.passwordsholder.passwords.domain.Password

@Composable
fun PasswordListItem(
    password: Password,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PasswordPhoto(
            password = password,
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = password.name,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 24.sp,
        )
    }
}