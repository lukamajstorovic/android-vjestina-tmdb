package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    onClickCard: () -> Unit,
    onClickFavoriteButton: () -> Unit
) {
    val favoritesViewState by remember { mutableStateOf(favoritesViewState) }

    FavoritesScreen(
        modifier = Modifier,
        favoritesViewState = favoritesViewState,
        onClickCard = { onClickCard() },
        onClickFavoriteButton = { onClickFavoriteButton() }
    )
}

@Composable
private fun FavoritesScreen(
    modifier: Modifier,
    favoritesViewState: FavoritesViewState,
    onClickCard: () -> Unit,
    onClickFavoriteButton: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        content = {
            item {
                Text(
                    text = "Favorites",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black
                )
            }
            items(
                items = favoritesViewState.favoriteMoviesViewStateCollection,
                key = { favoritesMovieViewState ->
                    favoritesMovieViewState.id
                }
            ) { currentMovie ->
                MovieCard(
                    movieCardViewState = currentMovie.movieCardViewState,
                    modifier = Modifier
                        .padding(3.dp)
                        .size(width = 140.dp, height = 200.dp),
                    onClickCard = {
                        onClickCard()
                    },
                    onClickFavoriteButton = {
                        onClickFavoriteButton()
                    }
                )
            }
        }
    )
}

@Preview
@Composable
private fun FavoritesScreenPreview() {
    val favoritesScreenModifier = Modifier
        .fillMaxSize()
        .padding(15.dp)

    MovieAppTheme {
        FavoritesScreen(
            modifier = favoritesScreenModifier,
            favoritesViewState = favoritesViewState,
            onClickCard = { },
            onClickFavoriteButton = { }
        )
    }
}
