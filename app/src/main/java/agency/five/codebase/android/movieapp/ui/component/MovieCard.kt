package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl: String?,
    val title: String,
    val isFavorite: Boolean
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit,
    onClickFavoriteButton: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClickCard() },
        shape = RoundedCornerShape(13)
    ) {
        Box {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.title,
                contentScale = ContentScale.Crop,
            )
            FavoriteButton(
                isFavorite = movieCardViewState.isFavorite,
                modifier = Modifier
                    .size(30.dp),
                onClick = { onClickFavoriteButton() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    val movieInstance = MoviesMock.getMoviesList()[0]
    MovieCard(
        movieCardViewState = MovieCardViewState(
            imageUrl = movieInstance.imageUrl,
            title = movieInstance.title,
            isFavorite = movieInstance.isFavorite
        ),
        modifier = Modifier
            .padding(3.dp)
            .size(width = 140.dp, height = 200.dp),
        onClickCard = { },
        onClickFavoriteButton = { }
    )
}
