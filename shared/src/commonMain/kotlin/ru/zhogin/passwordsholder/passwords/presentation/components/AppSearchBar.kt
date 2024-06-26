package ru.zhogin.passwordsholder.passwords.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    modifier: Modifier = Modifier,
    onSearchPassword: (String) -> Unit,
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    SearchBar(
        query = text,
        onQueryChange = {
            text = it
            onSearchPassword(it)
        },
        onSearch = {
            onSearchPassword(it)
            text = ""
        },
        active = false,
        onActiveChange = {
        },
        placeholder = {
            Text(text = "Search", color = MaterialTheme.colorScheme.secondary)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.secondary,
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable {
                        text = ""
                        onSearchPassword(text)
                    },
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close Icon",
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color.Transparent,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.secondary
            )
        ),
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {

    }
}