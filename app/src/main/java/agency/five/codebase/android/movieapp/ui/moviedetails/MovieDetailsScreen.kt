package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute(
    onClickFavoriteButton: () -> Unit
) {
    val movieDetailsViewState by remember { mutableStateOf(movieDetailsViewState) }
    MovieDetailsScreen(
        modifier = Modifier,
        movieDetailsViewState = movieDetailsViewState,
        onClickFavoriteButton = onClickFavoriteButton
    )
}

@Composable
private fun MovieDetailsScreen(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState,
    onClickFavoriteButton: () -> Unit
) {
    Column {
        MovieImage(
            modifier = Modifier,
            movieDetailsViewState = movieDetailsViewState,
            onClickFavoriteButton = onClickFavoriteButton
        )
        Overview(
            modifier = Modifier
                .padding(bottom = 10.dp),
            movieDetailsViewState = movieDetailsViewState
        )
        Crew(
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp),
            movieDetailsViewState = movieDetailsViewState
        )
        Cast(
            modifier = Modifier
                .padding(bottom = 10.dp),
            movieDetailsViewState = movieDetailsViewState
        )
    }
}

@Composable
private fun MovieImage(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState,
    onClickFavoriteButton: () -> Unit
) {
    Box(

    ) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = movieDetailsViewState.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(370.dp)
        )
        FavoriteButton(
            isFavorite = movieDetailsViewState.isFavorite,
            modifier = Modifier
                .size(40.dp),
            onClick = { onClickFavoriteButton() }
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(0.7f))
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
            ) {
                UserScoreProgressBar(
                    userScoreProgressBarViewState = UserScoreProgressBarViewState(
                        score = movieDetailsViewState.voteAverage
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp, top = 5.dp)
                )
                Text(
                    text = "User score",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 17.dp)
                )
            }
            Text(
                text = movieDetailsViewState.title,
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
    movieDetailsViewState: MovieDetailsViewState,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Overview",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
        )
        Text(
            text = movieDetailsViewState.overview,
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
    movieDetailsViewState: MovieDetailsViewState,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        content = {
            items(
                items = movieDetailsViewState.crew,
                key = { crewman ->
                    crewman.id
                }
            ) { crewItemInstance ->
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
    )
}

@Composable
private fun Cast(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Top Billed Cast",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
        )
        LazyRow(
            content = {
                items(
                    items = movieDetailsViewState.cast,
                    key = { actor ->
                        actor.id
                    }
                ) { actorInstance ->
                    ActorCard(
                        actorCardViewState = ActorCardViewState(
                            id = actorInstance.id,
                            imageUrl = actorInstance.imageUrl,
                            name = actorInstance.name,
                            character = actorInstance.character
                        ),
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp)
                    )
                }
            }
        )
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
