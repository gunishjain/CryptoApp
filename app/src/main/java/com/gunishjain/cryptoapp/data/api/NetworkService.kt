package com.gunishjain.cryptoapp.data.api

import com.gunishjain.cryptoapp.data.models.Coin
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("v1/coins")
    suspend fun getCoinsList() : List<Coin>

    @GET("v1/tickers/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String) : Coin
}