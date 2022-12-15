package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val GRID_COUNT: Int = 3
const val WEIGHT_ONE: Float = 1f

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel,
    openMovieDetails: (Int) -> Unit,
) {
    val favoritesViewState: FavoritesViewState by viewModel.favoritesViewState.collectAsState()

    FavoritesScreen(
        modifier = Modifier,
        favoritesViewState = favoritesViewState,
        onClickCard = openMovieDetails,
        onClickFavoriteButton = { movieId -> viewModel.toggleFavorite(movieId) }
    )
}

@Composable
private fun FavoritesScreen(
    modifier: Modifier,
    favoritesViewState: FavoritesViewState,
    onClickCard: (Int) -> Unit,
    onClickFavoriteButton: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 20.dp, bottom = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.favorites),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_COUNT),
            modifier = Modifier.weight(WEIGHT_ONE)
        ) {
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
                        onClickCard(currentMovie.id)
                    },
                    onClickFavoriteButton = {
                        onClickFavoriteButton(currentMovie.id)
                    }
                )
            }
        }
    }
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
