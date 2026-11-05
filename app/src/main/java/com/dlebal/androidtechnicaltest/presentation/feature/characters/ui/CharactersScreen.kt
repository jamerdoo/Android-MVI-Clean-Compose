package com.dlebal.androidtechnicaltest.presentation.feature.characters.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi.CharacterIntent
import com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi.CharacterViewModel
import com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi.UiResult
import com.dlebal.androidtechnicaltest.ui.core.component.PagingLoadingState
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.material3.placeholder
import com.eygraber.compose.placeholder.material3.shimmer

@Composable
fun CharactersScreen (
    viewModel: CharacterViewModel,
    onPostClick: (CharacterModel) -> Unit
){
    val state by viewModel.state.collectAsState()

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column {
                when (val list = state.characterList) {
                    UiResult.Loading -> CircularProgressIndicator()
                    is UiResult.Success -> CharacterPagingList(viewModel, list.data.collectAsLazyPagingItems(), onPostClick)
                    UiResult.Empty -> {}
                    is UiResult.Error -> {}
                }
            }
        }
    }

}

@Composable
fun CharacterPagingList(
    viewModel: CharacterViewModel,
    list: LazyPagingItems<CharacterModel>,
    onPostClick: (CharacterModel) -> Unit
) {
    val refreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = {
            viewModel.sendIntent(CharacterIntent.LoadAll)
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            when (list.loadState.refresh) {
                is LoadState.Loading if list.itemCount == 0 -> {
                    item(span = {GridItemSpan (2)}){
                        PagingLoadingState(
                            modifier = Modifier.fillMaxSize(),
                            progressColor = Color.Blue,
                            sizeProgress = 64.dp,
                            contentAlignment = Alignment.Center
                        )
                    }
                }

                is LoadState.NotLoading if list.itemCount == 0 -> {
                    item(span = {GridItemSpan (4)}){
                        Text("Not Characters")
                    }
                }

                is LoadState.Error -> {
                    //handle error
                }

                else -> {
                    items(
                        count = list.itemCount,
                        span = {index -> if(index == 0 ) GridItemSpan(2) else GridItemSpan(1)}
                    ) { i ->
                        list[i]?.let { characterModel ->
                            CharacterItemList(characterModel, onPostClick)
                        }
                    }

                    if(list.loadState.append is LoadState.Loading) {
                        item(span = {GridItemSpan (2)}){
                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .height(200.dp), contentAlignment = Alignment.Center){
                                CircularProgressIndicator(Modifier.size(200.dp), color = Color.Green)
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CharacterItemList(data: CharacterModel,
                      onPostClick: (CharacterModel) -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(24))
            .border(
                3.dp, color = Color.Red,
                shape = RoundedCornerShape(24, 24, 24, 24)
            )
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                onPostClick(data)
            },
        contentAlignment = Alignment.BottomCenter,
    ) {
        var isLoading by remember { mutableStateOf(true) }
        AsyncImage(
            model = data.image,
            contentDescription = "",
            onState = {
                isLoading = it is AsyncImagePainter.State.Loading
            },
            modifier = Modifier
                .fillMaxSize()
                .placeholder(
                    visible = isLoading,
                    highlight = PlaceholderHighlight.shimmer(),
                ),
            contentScale = ContentScale.Crop
        )
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Card(Modifier.fillMaxWidth()) {
                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, 12.dp),
                    text = "Name: ".plus(data.name).plus("\nSpecies:").plus(data.species),
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    fontFamily = FontFamily.SansSerif,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            }
        }
    }
}
