package com.gunishjain.cryptoapp.ui.coinlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunishjain.cryptoapp.data.models.Coin
import com.gunishjain.cryptoapp.data.models.CoinDetail
import com.gunishjain.cryptoapp.data.repository.CryptoCoinRepository
import com.gunishjain.cryptoapp.ui.base.UiState
import com.gunishjain.cryptoapp.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CryptoCoinRepository,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Coin>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Coin>>> = _uiState

    private val _coinState = MutableStateFlow<UiState<CoinDetail>>(UiState.Loading)
    val coinState: StateFlow<UiState<CoinDetail>> = _coinState

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getCoinList()
    }

    fun getCoinList() {
        viewModelScope.launch(dispatcherProvider.main) {
            _isLoading.value = true
            _uiState.value = UiState.Loading
            repository.getCoinList()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                    _isLoading.value = false
                }
        }
    }

    fun getCoinDetails(coinId: String) {
        viewModelScope.launch(dispatcherProvider.main) {
            _coinState.value = UiState.Loading
            repository.getCoinDetail(coinId)
                .catch { e ->
                    _coinState.value = UiState.Error(e.toString())
                }
                .collect {
                    _coinState.value = UiState.Success(it)
                }
        }
    }


}