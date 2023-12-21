package com.gunishjain.cryptoapp.ui.base

sealed interface UiState<out T> {

    data class Success<T>(var data: T) : UiState<T>

    data class Error(var message: String) : UiState<Nothing>

    object Loading: UiState<Nothing>

}