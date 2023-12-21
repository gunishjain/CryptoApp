package com.gunishjain.cryptoapp.ui.coinlist

import androidx.lifecycle.ViewModel
import com.gunishjain.cryptoapp.data.repository.CryptoCoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val repository: CryptoCoinRepository) :
    ViewModel() {


}