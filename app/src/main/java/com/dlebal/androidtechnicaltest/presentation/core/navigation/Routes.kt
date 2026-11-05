package com.dlebal.androidtechnicaltest.presentation.core.navigation

sealed class Routes (val route: String) {
    data object Home: Routes("home")
    data object Detail: Routes("Detail")
}