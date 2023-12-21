package com.gunishjain.cryptoapp.navigation

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gunishjain.cryptoapp.ui.coindetail.CoinDetailRoute
import com.gunishjain.cryptoapp.ui.coinlist.CoinListRoute
import com.gunishjain.cryptoapp.ui.coinlist.CoinListScreen

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
            CoinListRoute(onCoinClick = {
                navController.navigate(route= "coindetail/$it")
            })
        }

        composable(route = Screen.CoinDetailScreen.route,
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
//            SearchScreenRoute(onNewsClick = {
//                openCustomChromeTab(context, it)
//            })
        }

    }
}

