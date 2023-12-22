package com.gunishjain.cryptoapp.navigation

sealed class Screen(val route: String) {

    object CoinListScreen : Screen(route = "coinlist")

    object CoinDetailScreen: Screen(route = "coindetail/{coinId}")

    object SearchCoin : Screen(route = "searchcoin")
}