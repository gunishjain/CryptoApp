package com.gunishjain.cryptoapp.ui.coinlist

import app.cash.turbine.test
import com.gunishjain.cryptoapp.data.models.Coin
import com.gunishjain.cryptoapp.data.repository.CryptoCoinRepository
import com.gunishjain.cryptoapp.ui.base.UiState
import com.gunishjain.cryptoapp.utils.DispatcherProvider
import com.gunishjain.cryptoapp.utils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CoinListViewModelTest {

    @Mock
    private lateinit var coinRepository: CryptoCoinRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }


    @Test
    fun fetchCoins_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            doReturn(flowOf(emptyList<Coin>()))
                .`when`(coinRepository)
                .getCoinList()
            val viewModel = CoinListViewModel(coinRepository, dispatcherProvider)
            viewModel.getCoinList()
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Coin>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(coinRepository, times(2)).getCoinList()
        }
    }

    @Test
    fun fetchCoins_whenRepositoryResponseSuccess_shouldSetSuccessUiState_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<Coin>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(coinRepository)
                .getCoinList()

            val viewModel = CoinListViewModel(coinRepository, dispatcherProvider)
            viewModel.getCoinList()
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(coinRepository, times(2)).getCoinList()
        }
    }

}