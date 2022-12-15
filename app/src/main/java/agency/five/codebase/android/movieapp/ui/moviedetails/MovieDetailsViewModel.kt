package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieId: Int,
    private val movieRepository: MovieRepository,
    private val movieDetailsMapper: MovieDetailsMapper,
) : ViewModel() {
    private val _movieDetailsViewState = MutableStateFlow(
        MovieDetailsViewState(
            id = 1,
            imageUrl = null,
            voteAverage = 0.0f,
            title = "",
            overview = "",
            isFavorite = false,
            crew = emptyList(),
            cast = emptyList()
        )
    )
    val movieDetailsViewState = _movieDetailsViewState.asStateFlow()

    init { getMovieDetails() }

    private fun getMovieDetails() {
        viewModelScope.launch {
            movieRepository.movieDetails(movieId).collect { movieDetails ->
                _movieDetailsViewState.value =
                    movieDetailsMapper.toMovieDetailsViewState(movieDetails)
            }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
