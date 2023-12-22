package com.gunishjain.cryptoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gunishjain.cryptoapp.ui.coindetail.CoinDetailRoute
import com.gunishjain.cryptoapp.ui.coinlist.CoinListRoute
import com.gunishjain.cryptoapp.ui.search.SearchScreenRoute

@Composable
fun SetupNavGraph(
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.CoinListScreen.route
    ) {
        composable(
            route = Screen.CoinListScreen.route
        ) {
            CoinListRoute(
                onFabClick = {
                    navController.navigate(route = "searchcoin")
                },
                onCoinClick = {
                    navController.navigate(route = "coindetail/$it")
                })
        }

        composable(
            route = Screen.CoinDetailScreen.route,
            arguments = listOf(
                navArgument("coinId") {
                    type = NavType.StringType
                })
        ) { it ->
            val coinId = it.arguments?.getString("coinId").toString()
            CoinDetailRoute(coinId)
        }

        composable(
            route = Screen.SearchCoin.route
        ) {
            SearchScreenRoute(onCoinClick = {

            })
        }

    }
}

