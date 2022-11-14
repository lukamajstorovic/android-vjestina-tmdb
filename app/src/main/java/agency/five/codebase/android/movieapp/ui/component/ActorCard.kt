package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl: String?,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(13)
    ) {
        Box {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = actorCardViewState.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Black.copy(0.7f))
                        .padding(start = 10.dp, top = 5.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = actorCardViewState.character,
                    fontSize = 9.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Black.copy(0.7f))
                        .padding(start = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ActorCardPreview() {
    val actorInstance = MoviesMock.getActor()
    ActorCard(
        actorCardViewState = ActorCardViewState(
            imageUrl = actorInstance.imageUrl,
            name = actorInstance.name,
            character = actorInstance.character
        ),
        modifier = Modifier
            .padding(3.dp)
            .size(
                width = 140.dp,
                height = 200.dp
            )
    )
}
