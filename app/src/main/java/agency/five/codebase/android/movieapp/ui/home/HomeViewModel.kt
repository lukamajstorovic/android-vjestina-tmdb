package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val homeScreenMapper: HomeScreenMapper,
) : ViewModel() {
    private val popularMoviesCategorySelected = MutableStateFlow(MovieCategory.POPULAR_STREAMING)
    private val nowPlayingCategorySelected = MutableStateFlow(MovieCategory.NOW_PLAYING_MOVIES)
    private val upcomingMoviesCategorySelected = MutableStateFlow(MovieCategory.UPCOMING_TODAY)

    private val popularMovieCategories = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATERS
    )

    private val upcomingMovieCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK,
    )

    private val nowPlayingMovieCategories = listOf(
        MovieCategory.NOW_PLAYING_MOVIES,
        MovieCategory.NOW_PLAYING_TV,
    )

    val popularCategoryViewState: StateFlow<HomeMovieCategoryViewState> =
        popularMoviesCategorySelected
            .flatMapLatest { selectedMovieCategory ->
                movieRepository.movies(selectedMovieCategory)
                    .map { popularMovies ->
                        homeScreenMapper.toHomeMovieCategoryViewState(
                            movieCategories = popularMovieCategories,
                            selectedMovieCategory = selectedMovieCategory,
                            movies = popularMovies,
                        )
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = popularMovieCategories,
                    selectedMovieCategory = popularMoviesCategorySelected.value,
                    movies = emptyList(),
                )
            )

    val nowPlayingCategoryViewState: StateFlow<HomeMovieCategoryViewState> =
        nowPlayingCategorySelected
            .flatMapLatest { selectedMovieCategory ->
                movieRepository.movies(selectedMovieCategory)
                    .map { nowPlayingMovies ->
                        homeScreenMapper.toHomeMovieCategoryViewState(
                            movieCategories = nowPlayingMovieCategories,
                            selectedMovieCategory = selectedMovieCategory,
                            movies = nowPlayingMovies,
                        )
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = nowPlayingMovieCategories,
                    selectedMovieCategory = nowPlayingCategorySelected.value,
                    movies = emptyList(),
                )
            )

    val upcomingCategoryViewState: StateFlow<HomeMovieCategoryViewState> =
        upcomingMoviesCategorySelected
            .flatMapLatest { selectedMovieCategory ->
                movieRepository.movies(selectedMovieCategory)
                    .map { upcomingMovies ->
                        homeScreenMapper.toHomeMovieCategoryViewState(
                            movieCategories = upcomingMovieCategories,
                            selectedMovieCategory = selectedMovieCategory,
                            movies = upcomingMovies,
                        )
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = upcomingMovieCategories,
                    selectedMovieCategory = upcomingMoviesCategorySelected.value,
                    movies = emptyList(),
                )
            )

    fun changeCategory(categoryId: Int) {
        when (categoryId) {
            MovieCategory.POPULAR_STREAMING.ordinal,
            MovieCategory.POPULAR_FOR_RENT.ordinal,
            MovieCategory.POPULAR_ON_TV.ordinal,
            MovieCategory.POPULAR_IN_THEATERS.ordinal
            -> {
                popularMoviesCategorySelected.update { MovieCategory.values()[categoryId] }
            }

            MovieCategory.NOW_PLAYING_MOVIES.ordinal,
            MovieCategory.NOW_PLAYING_TV.ordinal
            -> {
                nowPlayingCategorySelected.update { MovieCategory.values()[categoryId] }
            }

            MovieCategory.UPCOMING_TODAY.ordinal,
            MovieCategory.UPCOMING_THIS_WEEK.ordinal
            -> {
                upcomingMoviesCategorySelected.update { MovieCategory.values()[categoryId] }
            }

            else -> throw IllegalStateException()
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
