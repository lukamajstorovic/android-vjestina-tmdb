package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CrewItemViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = crewItemViewState.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
        )
        Text(
            text = crewItemViewState.job,
            fontSize = 9.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CrewItemPreview() {
    val crewItemInstance = MoviesMock.getCrewman()
    CrewItem(
        crewItemViewState = CrewItemViewState(
            name = crewItemInstance.name,
            job = crewItemInstance.job
        ),
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
    )
}
