package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

var popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATERS,
    ),
    selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
    movies = MoviesMock.getMoviesList()
)

var nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.NOW_PLAYING_MOVIES,
        MovieCategory.NOW_PLAYING_TV
    ),
    selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES,
    movies = MoviesMock.getMoviesList()
)

var upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK
    ),
    selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
    movies = MoviesMock.getMoviesList()
)

@Composable
fun HomeRoute(
    onClickCard: () -> Unit,
    onClickFavoriteButton: () -> Unit,
) {
    val popularCategoryViewState by remember { mutableStateOf(popularCategoryViewState) }
    val nowPlayingCategoryViewState by remember { mutableStateOf(nowPlayingCategoryViewState) }
    val upcomingCategoryViewState by remember { mutableStateOf(upcomingCategoryViewState) }
    HomeScreen(
        popularCategoryViewState = popularCategoryViewState,
        nowPlayingCategoryViewState = nowPlayingCategoryViewState,
        upcomingCategoryViewState = upcomingCategoryViewState,
        onClickFavoriteButton = { onClickFavoriteButton() },
        onClickCard = { onClickCard() },
    )
}

@Composable
private fun HomeScreen(
    popularCategoryViewState: HomeMovieCategoryViewState,
    nowPlayingCategoryViewState: HomeMovieCategoryViewState,
    upcomingCategoryViewState: HomeMovieCategoryViewState,
    onClickFavoriteButton: () -> Unit,
    onClickCard: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            ),
        content = {
            MovieCategoryBlock(
                modifier = Modifier,
                homeMovieCategoryViewState = popularCategoryViewState,
                categoryName = "Popular",
                onClickFavoriteButton = onClickFavoriteButton,
                onClickCard = onClickCard
            )
            MovieCategoryBlock(
                modifier = Modifier,
                homeMovieCategoryViewState = nowPlayingCategoryViewState,
                categoryName = "Now Playing",
                onClickFavoriteButton = onClickFavoriteButton,
                onClickCard = onClickCard
            )
            MovieCategoryBlock(
                modifier = Modifier,
                homeMovieCategoryViewState = upcomingCategoryViewState,
                categoryName = "Upcoming",
                onClickFavoriteButton = onClickFavoriteButton,
                onClickCard = onClickCard
            )
        }
    )
}

@Composable
private fun MovieCategoryBlock(
    modifier: Modifier,
    homeMovieCategoryViewState: HomeMovieCategoryViewState,
    categoryName: String,
    onClickFavoriteButton: () -> Unit,
    onClickCard: () -> Unit
) {
    Text(
        text = categoryName,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Serif,
        color = Color.Black,
        modifier = Modifier
            .padding(start = 5.dp, top = 20.dp)
    )
    LazyRow(
        content = {
            items(
                items = homeMovieCategoryViewState.movieCategories,
                key = { category -> category.itemId }) { category ->
                MovieCategoryLabel(
                    movieCategoryLabelViewState = category,
                    modifier = Modifier.padding(5.dp),
                    onClick = { onCategoryClick(category) }
                )
            }
        }
    )
    LazyRow(
        content = {
            items(
                items = homeMovieCategoryViewState.movies,
                key = { movie -> movie.id }) { movie ->
                MovieCard(
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp)
                        .padding(5.dp),
                    movieCardViewState = MovieCardViewState(
                        imageUrl = movie.imageUrl,
                        title = movie.title,
                        isFavorite = movie.isFavorite
                    ),
                    onClickFavoriteButton = { onClickFavoriteButton() },
                    onClickCard = { onClickCard() }
                )
            }
        }
    )
}

private fun onCategoryClick(it: MovieCategoryLabelViewState) {
    when (it.itemId) {
        0 -> {
            popularCategoryViewState =
                homeScreenMapper.toHomeMovieCategoryViewState(
                    movies = MoviesMock.getMoviesList(),
                    movieCategories = listOf(
                        MovieCategory.POPULAR_STREAMING,
                        MovieCategory.POPULAR_ON_TV,
                        MovieCategory.POPULAR_FOR_RENT,
                        MovieCategory.POPULAR_IN_THEATERS,
                    ),
                    selectedMovieCategory = MovieCategory.POPULAR_STREAMING
                )
        }
        1 -> {
            popularCategoryViewState =
                homeScreenMapper.toHomeMovieCategoryViewState(
                    movies = MoviesMock.getMoviesList(),
                    movieCategories = listOf(
                        MovieCategory.POPULAR_STREAMING,
                        MovieCategory.POPULAR_ON_TV,
                        MovieCategory.POPULAR_FOR_RENT,
                        MovieCategory.POPULAR_IN_THEATERS,
                    ),
                    selectedMovieCategory = MovieCategory.POPULAR_ON_TV
                )
        }
        2 -> {
            popularCategoryViewState =
                homeScreenMapper.toHomeMovieCategoryViewState(
                    movies = MoviesMock.getMoviesList(),
                    movieCategories = listOf(
                        MovieCategory.POPULAR_STREAMING,
                        MovieCategory.POPULAR_ON_TV,
                        MovieCategory.POPULAR_FOR_RENT,
                        MovieCategory.POPULAR_IN_THEATERS,
                    ),
                    selectedMovieCategory = MovieCategory.POPULAR_FOR_RENT
                )
        }
        3 -> {
            popularCategoryViewState =
                homeScreenMapper.toHomeMovieCategoryViewState(
                    movies = MoviesMock.getMoviesList(),
                    movieCategories = listOf(
                        MovieCategory.POPULAR_STREAMING,
                        MovieCategory.POPULAR_ON_TV,
                        MovieCategory.POPULAR_FOR_RENT,
                        MovieCategory.POPULAR_IN_THEATERS,
                    ),
                    selectedMovieCategory = MovieCategory.POPULAR_IN_THEATERS
                )
        }
        4 -> {
            nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                movies = MoviesMock.getMoviesList(),
                movieCategories = listOf(
                    MovieCategory.NOW_PLAYING_MOVIES,
                    MovieCategory.NOW_PLAYING_TV
                ),
                selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES
            )
        }
        5 -> {
            nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                movies = MoviesMock.getMoviesList(),
                movieCategories = listOf(
                    MovieCategory.NOW_PLAYING_MOVIES,
                    MovieCategory.NOW_PLAYING_TV
                ),
                selectedMovieCategory = MovieCategory.NOW_PLAYING_TV
            )
        }
        6 -> {
            upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                movies = MoviesMock.getMoviesList(),
                movieCategories = listOf(
                    MovieCategory.UPCOMING_TODAY,
                    MovieCategory.UPCOMING_THIS_WEEK
                ),
                selectedMovieCategory = MovieCategory.UPCOMING_TODAY
            )
        }
        else -> {
            upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                movies = MoviesMock.getMoviesList(),
                movieCategories = listOf(
                    MovieCategory.UPCOMING_TODAY,
                    MovieCategory.UPCOMING_THIS_WEEK
                ),
                selectedMovieCategory = MovieCategory.UPCOMING_THIS_WEEK
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        popularCategoryViewState = popularCategoryViewState,
        nowPlayingCategoryViewState = nowPlayingCategoryViewState,
        upcomingCategoryViewState = upcomingCategoryViewState,
        onClickFavoriteButton = { },
        onClickCard = { }
    )
}
