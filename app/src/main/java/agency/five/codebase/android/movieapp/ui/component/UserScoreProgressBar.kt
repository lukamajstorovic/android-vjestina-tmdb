package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class UserScoreProgressBarViewState(
    val score: Float
)

@Composable
fun UserScoreProgressBar(
    userScoreProgressBarViewState: UserScoreProgressBarViewState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        CircularProgressIndicator(
            progress = userScoreProgressBarViewState.score,
            color = Color.Black,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),

            )
        Text(
            text = (userScoreProgressBarViewState.score * 10).toString(),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(10.dp)
        )
        CircularProgressIndicator(
            progress = 1f,
            color = Color.Black.copy(0.7f),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserScoreProgressBarPreview() {
    UserScoreProgressBar(
        userScoreProgressBarViewState = UserScoreProgressBarViewState(
            score = 0.75f
        )
    )
}
