package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl: FavoritesMapper {
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        val favoriteMoviesViewStateCollection = mutableListOf<FavoritesMovieViewState>()
        for (favoriteMovie in favoriteMovies)
            favoriteMoviesViewStateCollection.add(mapMovie(favoriteMovie))
        return FavoritesViewState(
            favoriteMoviesViewStateCollection = favoriteMoviesViewStateCollection
        )
    }
    private fun mapMovie(movie: Movie): FavoritesMovieViewState {
        return FavoritesMovieViewState(
            id = movie.id,
            movieCardViewState = MovieCardViewState(
                imageUrl = movie.imageUrl,
                title = movie.title,
                isFavorite = movie.isFavorite
            )
        )
    }
}
