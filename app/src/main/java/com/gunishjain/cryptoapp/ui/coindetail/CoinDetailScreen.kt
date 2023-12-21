package com.gunishjain.cryptoapp.ui.coindetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.gunishjain.cryptoapp.data.models.CoinDetail
import com.gunishjain.cryptoapp.ui.base.ShowProgressBar
import com.gunishjain.cryptoapp.ui.base.ShowToast
import com.gunishjain.cryptoapp.ui.base.UiState
import com.gunishjain.cryptoapp.ui.coinlist.CoinListViewModel

@Composable
fun CoinDetailRoute(
    coinId: String,
    viewModel: CoinListViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit, block = {
        viewModel.getCoinDetails(coinId)
    })

    val coin = viewModel.coinState.collectAsStateWithLifecycle()

    Surface {
        Column(modifier = Modifier.padding(4.dp)) {
            CoinDetailCard(coin.value)
        }
    }


}

@Composable
fun CoinDetailCard(uiState: UiState<CoinDetail>) {

    when (uiState) {
        is UiState.Success -> {
            CoinDetailItem(uiState.data)
        }

        is UiState.Loading -> {
            ShowProgressBar()
        }

        is UiState.Error -> {
            ShowToast(uiState.message)
        }

    }

}

@Composable
fun CoinDetailItem(coin: CoinDetail) {
    Column(modifier = Modifier
        .fillMaxWidth()
        ) {
        NameText(coin.name)
        DescriptionText(coin)
//        SourceText(article.source)
    }
}

@Composable
fun NameText(name: String?) {
    if (!name.isNullOrEmpty()) {
        Text(
            text = name,
            fontSize = 50.sp,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun DescriptionText(coin: CoinDetail) {

    Row(modifier = Modifier.fillMaxWidth().padding(start = 2.dp),
    ) {

        Box(modifier = Modifier.size(75.dp,25.dp)
        ) {
            Text(
                text = "RANK ${coin.rank}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 2
            )
        }

        Text(
            text = "$ ${coin.quotes.USD.price}",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
            maxLines = 1,
            modifier = Modifier.padding(2.dp)
        )

    }

}

//@Composable
//fun SourceText(source: Source?) {
//    if (source != null) {
//        Text(
//            text = source.name,
//            style = MaterialTheme.typography.titleSmall,
//            color = Color.Gray,
//            maxLines = 1,
//            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
//        )
//    }
//}