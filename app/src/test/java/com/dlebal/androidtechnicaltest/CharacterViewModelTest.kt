package com.dlebal.androidtechnicaltest

import androidx.paging.PagingData
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import com.dlebal.androidtechnicaltest.domain.usecase.GetAllCharactersUseCase
import com.dlebal.androidtechnicaltest.domain.usecase.GetSingleCharacterUseCase
import com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi.CharacterIntent
import com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi.CharacterViewModel
import com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi.UiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getAllCharactersUseCase: GetAllCharactersUseCase = mockk()
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase = mockk()

    private lateinit var viewModel: CharacterViewModel

    @Before
    fun setup() {
        viewModel = CharacterViewModel(
            getSingleCharacterUseCase,
            getAllCharactersUseCase
        )
    }

    @Test
    fun `when LoadAll intent is sent, state emits Success with PagingData`() = runTest {

        // GIVEN
        val fakePagingData = PagingData.empty<CharacterModel>()
        val fakeFlow = flowOf(fakePagingData)

        coEvery { getAllCharactersUseCase.invoke() } returns fakeFlow

        // WHEN
        viewModel.sendIntent(CharacterIntent.LoadAll)

        advanceUntilIdle()

        // THEN
        val state = viewModel.state.value

        assert(state.characterList is UiResult.Success)
    }
}