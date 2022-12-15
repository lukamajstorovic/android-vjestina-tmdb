package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

const val GRID_COUNT: Int = 2
const val CREW_COUNT: Int = 6

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute(
    viewModel: MovieDetailsViewModel
) {
    val movieDetailsViewState: MovieDetailsViewState by viewModel.movieDetailsViewState.collectAsState()
    MovieDetailsScreen(
        modifier = Modifier,
        movieDetailsViewState = movieDetailsViewState,
        onClickFavoriteButton = { movieId -> viewModel.toggleFavorite(movieId) }
    )
}

@Composable
private fun MovieDetailsScreen(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState,
    onClickFavoriteButton: (Int) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(
            state = rememberScrollState(),
            enabled = true
        )
    ) {
        MovieImage(
            modifier = Modifier,
            id = movieDetailsViewState.id,
            imageUrl = movieDetailsViewState.imageUrl,
            title = movieDetailsViewState.title,
            voteAverage = movieDetailsViewState.voteAverage,
            isFavorite = movieDetailsViewState.isFavorite,
            onClickFavoriteButton = onClickFavoriteButton
        )

        Overview(
            modifier = Modifier
                .padding(bottom = 10.dp),
            overview = movieDetailsViewState.overview
        )
        Crew(
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp),
            crew = movieDetailsViewState.crew
        )
        Cast(
            modifier = Modifier
                .padding(bottom = 10.dp),
            cast = movieDetailsViewState.cast
        )
    }
}

@Composable
private fun MovieImage(
    modifier: Modifier,
    id: Int,
    imageUrl: String?,
    title: String,
    voteAverage: Float,
    isFavorite: Boolean,
    onClickFavoriteButton: (Int) -> Unit
) {
    Box(

    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
        )
        FavoriteButton(
            isFavorite = isFavorite,
            modifier = Modifier
                .size(40.dp),
            onClick = { onClickFavoriteButton(id) }
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(TRANSPARENCY))
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
            ) {
                UserScoreProgressBar(
                    userScoreProgressBarViewState = UserScoreProgressBarViewState(
                        score = voteAverage
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp, top = 5.dp)
                )
                Text(
                    text = stringResource(R.string.user_score),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 17.dp)
                )
            }
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 15.dp)
            )
        }
    }
}

@Composable
private fun Overview(
    modifier: Modifier,
    overview: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.overview),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
        )
        Text(
            text = overview,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
        )
    }
}

@Composable
private fun Crew(
    modifier: Modifier,
    crew: List<CrewItemViewState>
) {
    LazyHorizontalGrid(
        modifier = modifier
            .height(65.dp)
            .fillMaxSize(),
        rows = GridCells.Fixed(GRID_COUNT),
        horizontalArrangement = Arrangement.SpaceBetween,
        userScrollEnabled = false,
    ) {
        items(
            items = crew,
        ) { crewItemInstance ->
            if (crewItemInstance.id < CREW_COUNT) {
                CrewItem(
                    crewItemViewState = CrewItemViewState(
                        id = crewItemInstance.id,
                        name = crewItemInstance.name,
                        job = crewItemInstance.job
                    ),
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun Cast(
    modifier: Modifier,
    cast: List<ActorCardViewState>
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.top_billed_cast),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
        )
        LazyRow {
            items(
                items = cast,
            ) { actorInstance ->
                ActorCard(
                    actorCardViewState = ActorCardViewState(
                        id = actorInstance.id,
                        imageUrl = actorInstance.imageUrl,
                        name = actorInstance.name,
                        character = actorInstance.character
                    ),
                    modifier = Modifier
                        .height(200.dp)
                        .width(140.dp)
                        .padding(start = 5.dp, end = 5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(
        modifier = Modifier,
        movieDetailsViewState = movieDetailsViewState,
        onClickFavoriteButton = {}
    )
}
