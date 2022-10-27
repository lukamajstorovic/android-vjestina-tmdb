package agency.five.codebase.android.movieapp.ui.component
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Movie
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun MovieCard(
    movieCardViewState: Movie,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(width = 140.dp, height = 200.dp),
        shape = RoundedCornerShape(13)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieCardViewState.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .clickable {
                    }
            )
        }
        Box(
            modifier = Modifier
                .padding(3.dp)
        ) {
            FavoriteButton(
                isFavorite = movieCardViewState.isFavorite
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    MovieCard(
        movieCardViewState = Movie(
            id = MoviesMock.getMoviesList()[0].id,
            title = MoviesMock.getMoviesList()[0].title,
            overview = MoviesMock.getMoviesList()[0].overview,
            imageUrl = MoviesMock.getMoviesList()[0].imageUrl,
            isFavorite = MoviesMock.getMoviesList()[0].isFavorite
        )
    )
}