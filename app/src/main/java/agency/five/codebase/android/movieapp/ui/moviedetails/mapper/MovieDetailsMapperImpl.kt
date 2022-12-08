package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewItemViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        val crewCollection = mutableListOf<CrewItemViewState>()

        for (crewman in movieDetails.crew)
            crewCollection.add(
                CrewItemViewState(
                    id = crewman.id,
                    name = crewman.name,
                    job = crewman.job
                )
            )

        val castCollection = mutableListOf<ActorCardViewState>()

        for (actor in movieDetails.cast)
            castCollection.add(
                ActorCardViewState(
                    id = actor.id,
                    imageUrl = actor.imageUrl,
                    name = actor.name,
                    character = actor.character
                )
            )

        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl,
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = crewCollection,
            cast = castCollection
        )
    }
}
