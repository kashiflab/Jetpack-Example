package com.example.jetpack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.jetpack.data.model.TvShowResponse
import com.example.jetpack.data.model.TvShowResponseItem

@Composable
fun RecyclerView(paddingValues: PaddingValues, data: TvShowResponse){
    LazyColumn {
        items (data){ it->
            ShowItem(data = it)
        }
    }
}


@Composable
fun ShowItem(data: TvShowResponseItem) {
    Card() {
            Image(
        painter = rememberAsyncImagePainter(
            data.image.medium,
            contentScale = ContentScale.None
        ),
            contentDescription = null,
            modifier = Modifier.requiredSize(50.dp)
        )
    }
}