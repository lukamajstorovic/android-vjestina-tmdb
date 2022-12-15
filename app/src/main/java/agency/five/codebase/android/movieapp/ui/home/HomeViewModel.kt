package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val homeScreenMapper: HomeScreenMapper,
) : ViewModel() {
    private val popularSelectedCategory: MutableState<MovieCategory> = mutableStateOf(MovieCategory.POPULAR_STREAMING)
    private val upcomingSelectedCategory: MutableState<MovieCategory> = mutableStateOf(MovieCategory.UPCOMING_TODAY)
    private val nowPlayingSelectedCategory: MutableState<MovieCategory> = mutableStateOf(MovieCategory.NOW_PLAYING_TV)

    private val _popularMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState(emptyList(), emptyList()))
    val popularMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _popularMoviesHomeViewState.asStateFlow()

    private val _nowPlayingMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState(emptyList(), emptyList()))
    val nowPlayingMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _nowPlayingMoviesHomeViewState.asStateFlow()

    private val _upcomingMovieHomeViewState = MutableStateFlow(HomeMovieCategoryViewState(emptyList(), emptyList()))
    val upcomingMovieHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _upcomingMovieHomeViewState.asStateFlow()

    init {
        initializeMovieCategories()
    }

    private fun initializeMovieCategories() {
        getPopular(homeScreenMapper)
        getUpcoming(homeScreenMapper)
        getNowPlaying(homeScreenMapper)
    }

    private fun getPopular(homeScreenMapper: HomeScreenMapper) {
        viewModelScope.launch {
            movieRepository.popularMovies(MovieCategory.POPULAR_STREAMING).collect {
                _popularMoviesHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = listOf(
                        MovieCategory.POPULAR_STREAMING,
                        MovieCategory.POPULAR_ON_TV,
                        MovieCategory.POPULAR_FOR_RENT,
                        MovieCategory.POPULAR_IN_THEATERS
                    ),
                    selectedMovieCategory = popularSelectedCategory.value,
                    movies = it
                )
            }
        }
    }

    private fun getNowPlaying(homeScreenMapper: HomeScreenMapper) {
        viewModelScope.launch {
            movieRepository.nowPlayingMovies(MovieCategory.NOW_PLAYING_MOVIES).collect {
                _nowPlayingMoviesHomeViewState.value =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = listOf(
                            MovieCategory.NOW_PLAYING_MOVIES,
                            MovieCategory.NOW_PLAYING_TV,
                        ),
                        selectedMovieCategory = nowPlayingSelectedCategory.value,
                        movies = it
                    )
            }
        }
    }

    private fun getUpcoming(homeScreenMapper: HomeScreenMapper) {
        viewModelScope.launch {
            movieRepository.upcomingMovies(MovieCategory.UPCOMING_TODAY).collect {
                _upcomingMovieHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = listOf(
                        MovieCategory.UPCOMING_TODAY,
                        MovieCategory.UPCOMING_THIS_WEEK,
                    ),
                    selectedMovieCategory = upcomingSelectedCategory.value,
                    movies = it
                )
            }
        }
    }

    fun changeCategory(categoryId: Int){
        when (categoryId) {
            MovieCategory.POPULAR_STREAMING.ordinal,
            MovieCategory.POPULAR_FOR_RENT.ordinal,
            MovieCategory.POPULAR_ON_TV.ordinal,
            MovieCategory.POPULAR_IN_THEATERS.ordinal
            -> {
                popularSelectedCategory.value = MovieCategory.values()[categoryId]
                getPopular(homeScreenMapper)

            }

            MovieCategory.NOW_PLAYING_MOVIES.ordinal,
            MovieCategory.NOW_PLAYING_TV.ordinal
            -> {
                nowPlayingSelectedCategory.value = MovieCategory.values()[categoryId]
                getNowPlaying(homeScreenMapper)
            }

            MovieCategory.UPCOMING_TODAY.ordinal,
            MovieCategory.UPCOMING_THIS_WEEK.ordinal
            -> {
                upcomingSelectedCategory.value = MovieCategory.values()[categoryId]
                getUpcoming(homeScreenMapper)
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
