package com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi

sealed class UiResult<out T> {
    object Loading : UiResult<Nothing>()
    data class Success<T>(val data: T) : UiResult<T>()
    object Empty : UiResult<Nothing>()
    data class Error(val message: String) : UiResult<Nothing>()
}