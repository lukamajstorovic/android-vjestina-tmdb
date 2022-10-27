package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Actor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun ActorCard(
    actorCardViewState: Actor,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(width = 140.dp, height = 200.dp),
        shape = RoundedCornerShape(13)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(actorCardViewState.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
            )
        }
        Box {
            Column {
                Box (
                    modifier = Modifier
                        .height(150.dp)
                )
                Text(
                    text = actorCardViewState.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Black.copy(0.7f))
                        .padding(start = 10.dp, top = 5.dp)
                        .width(140.dp)
                        .height(20.dp)
                )
                Text(
                    text = actorCardViewState.character,
                    fontSize = 9.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Black.copy(0.7f))
                        .padding(start = 10.dp)
                        .width(140.dp)
                        .height(25.dp)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun ActorCardPreview() {
    ActorCard(
        actorCardViewState = Actor(
            id = MoviesMock.getActor().id,
            imageUrl = MoviesMock.getActor().imageUrl,
            name = MoviesMock.getActor().name,
            character = MoviesMock.getActor().character
        )
    )
}