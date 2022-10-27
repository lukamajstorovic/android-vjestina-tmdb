package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Crewman
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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


@Composable
fun CrewItem(
    crewItemViewState: Crewman,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            text = crewItemViewState.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
                .width(140.dp)
                .height(20.dp)
        )
        Text(
            text = crewItemViewState.job,
            fontSize = 9.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
                .width(140.dp)
                .height(20.dp)
        )
    }
}
@Preview
@Composable
private fun CrewItemPreview() {
    CrewItem(
        crewItemViewState = Crewman(
            id = MoviesMock.getCrewman().id,
            name = MoviesMock.getCrewman().name,
            job = MoviesMock.getCrewman().job
        )
    )
}