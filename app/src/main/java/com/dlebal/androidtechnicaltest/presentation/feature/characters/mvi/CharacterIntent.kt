package com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi

sealed class CharacterIntent {
    object LoadCharacter : CharacterIntent()
    object ReloadList : CharacterIntent()
    object LoadAll : CharacterIntent()
}