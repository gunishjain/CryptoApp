package com.gunishjain.cryptoapp.data.repository

import com.gunishjain.cryptoapp.data.api.NetworkService
import com.gunishjain.cryptoapp.data.db.dao.CoinsDao
import com.gunishjain.cryptoapp.data.models.Coin
import com.gunishjain.cryptoapp.data.models.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CryptoCoinRepository @Inject constructor(
    private val networkService: NetworkService,
    private val coinsDao: CoinsDao) {

    fun getCoinList() : Flow<List<Coin>> {
        return flow {
            emit(networkService.getCoinsList())
            coinsDao.insertCoins(networkService.getCoinsList())
        }
    }

    fun getCoinDetail(coinId: String) : Flow<CoinDetail> {
        return flow {
            emit(networkService.getCoinDetails(coinId))
        }
    }

    suspend fun searchCoins(searchQuery: String) : Flow<List<Coin>> {
        return flow {
            emit(coinsDao.searchCoinsByName(searchQuery))
        }
    }

}