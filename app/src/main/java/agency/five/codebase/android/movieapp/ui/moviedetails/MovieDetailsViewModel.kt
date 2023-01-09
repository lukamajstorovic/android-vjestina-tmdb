package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieId: Int,
    private val movieRepository: MovieRepository,
    private val movieDetailsMapper: MovieDetailsMapper,
) : ViewModel() {
    private val initialMovieDetailsViewState = MovieDetailsViewState(
        id = 1,
        imageUrl = null,
        voteAverage = 0.0f,
        title = "",
        overview = "",
        isFavorite = false,
        crew = emptyList(),
        cast = emptyList()
    )

    private val _movieDetailsViewState = MutableStateFlow(
        initialMovieDetailsViewState
    )

    val movieDetailsViewState =
        _movieDetailsViewState
            .flatMapLatest { movie ->
                movieRepository.movieDetails(movie.id)
                    .map { details ->
                        movieDetailsMapper.toMovieDetailsViewState(details)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = initialMovieDetailsViewState
            )

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
