package com.gunishjain.cryptoapp.ui.coinlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gunishjain.cryptoapp.data.models.Coin
import com.gunishjain.cryptoapp.ui.base.ShowProgressBar
import com.gunishjain.cryptoapp.ui.base.ShowToast
import com.gunishjain.cryptoapp.ui.base.UiState

@Composable
fun CoinListRoute(
    onCoinClick: (coinId: String) -> Unit,
    onFabClick: () -> Unit,
    viewModel: CoinListViewModel = hiltViewModel()
) {

    val coins = viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.padding(4.dp)) {
        CoinListScreen(coins.value, onCoinClick)
        FloatingActionButton(
            onClick = { onFabClick() },
            modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp)
        ) {
            Icon(Icons.Filled.Search, "Search")
        }
    }

}

@Composable
fun CoinListScreen(coinsState: UiState<List<Coin>>, onCoinClick: (coinId: String) -> Unit) {

    when (coinsState) {
        is UiState.Success -> {
            CoinList(coinsState.data, onCoinClick)
        }

        is UiState.Loading -> {
            ShowProgressBar()
        }

        is UiState.Error -> {
            ShowToast(coinsState.message)
        }

    }

}

@Composable
fun CoinList(coins: List<Coin>, onCoinClick: (coinId: String) -> Unit) {

    LazyColumn {
        items(coins.size) { index ->
            Coin(coins[index], onCoinClick)
        }
    }

}

@Composable
fun Coin(coin: Coin, onCoinClick: (coinId: String) -> Unit) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBFABCB),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(12.dp)
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                if (coin.id.isNotEmpty()) {
                    onCoinClick(coin.id)
                }
            }
        ) {
            Text(
                text = coin.rank.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                maxLines = 2,
                fontSize = 22.sp,
                modifier = Modifier.padding(2.dp)
            )

            Text(
                text = coin.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontSize = 22.sp,
                maxLines = 2,
                modifier = Modifier.padding(start = 12.dp)
            )

            Text(
                text = coin.symbol,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                maxLines = 2,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 12.dp, top = 8.dp)
            )

            val statusText = if (coin.isActive) {
                "Active"
            } else {
                "Inactive"
            }

            val color = if (coin.isActive) {
                Color.White
            } else {
                Color.Red
            }

            Text(
                text = statusText,
                color = color,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 12.dp, top = 8.dp)
            )

        }
    }

}


