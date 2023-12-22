package com.gunishjain.cryptoapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gunishjain.cryptoapp.ui.coinlist.CoinListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenRoute(
    onCoinClick: (url: String) -> Unit,
    viewModel: SearchCoinsViewModel = hiltViewModel(),
) {

    val searchResult = viewModel.uiState.collectAsStateWithLifecycle()
    val uiState = searchResult.value
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(4.dp)) {

        SearchBar(
            query = text,
            onQueryChange = {
                text = it
                viewModel.onQuerySearch(it)
            },
            onSearch = {
                active = false
            },
            active = active,
            placeholder = {
                Text(text = "Search Coins")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            content = {
                CoinListScreen(uiState, onCoinClick)
            },
            onActiveChange = {
                active = it
            },
            tonalElevation = 0.dp
        )
    }

}