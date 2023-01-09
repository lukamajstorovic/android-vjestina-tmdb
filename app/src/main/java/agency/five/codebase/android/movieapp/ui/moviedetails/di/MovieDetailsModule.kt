package agency.five.codebase.android.movieapp.ui.moviedetails.di

import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewModel
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel { (movieId: Int) ->
        MovieDetailsViewModel(
            movieId = movieId,
            movieRepository = get(),
            movieDetailsMapper = get()
        )
    }
    single<MovieDetailsMapper> { MovieDetailsMapperImpl() }
}
