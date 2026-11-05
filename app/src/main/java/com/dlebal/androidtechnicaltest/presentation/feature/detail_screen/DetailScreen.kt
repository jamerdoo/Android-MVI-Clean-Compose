package com.dlebal.androidtechnicaltest.presentation.feature.detail_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.material3.placeholder
import com.eygraber.compose.placeholder.material3.shimmer

@Composable
fun DetailScreen(data: CharacterModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        Box {
            var isLoading by remember { mutableStateOf(true) }
            AsyncImage(
                model = data.image,
                contentDescription = "",
                onState = {
                    isLoading = it is AsyncImagePainter.State.Loading
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .placeholder(
                        visible = isLoading,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
                contentScale = ContentScale.Crop
            )
        }
        DetailCharacter(data)
    }
}

@Composable
fun DetailCharacter(data: CharacterModel){
    Column(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = data.name,
                fontSize = 24.sp,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(16.dp),
            )
            AddTextDescription("Species", data.species)
            AddTextDescription("Gender", data.gender)
            AddTextDescription("Status", data.status)
        }

    }
}

@Composable
fun AddTextDescription(description: String, info: String){
    Spacer(modifier = Modifier.height(4.dp))
    Text(text = "$description: $info", modifier = Modifier.padding(start = 8.dp), style = MaterialTheme.typography.labelLarge)

}
