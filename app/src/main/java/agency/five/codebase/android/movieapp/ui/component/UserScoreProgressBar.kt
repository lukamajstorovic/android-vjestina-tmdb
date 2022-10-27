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


@Composable
fun UserScoreProgressBar(
    score: Float,
    modifier: Modifier = Modifier
) {
    Box{
        CircularProgressIndicator(
            progress = score,
            color = Color.Black,
            modifier = modifier
                .width(40.dp)
                .height(40.dp),

            )
        Text(
            text = (score * 10).toString(),
            fontSize = 14.sp,
            modifier = modifier
                .padding(10.dp)
        )
    }
    Box {
        CircularProgressIndicator(
            progress = 1f,
            color = Color.Black.copy(0.7f),
            modifier = modifier
                .width(40.dp)
                .height(40.dp),
            )

    }
}

@Preview(showBackground = true)
@Composable
fun UserScoreProgressBarPreview(){
    UserScoreProgressBar(
        score = 0.75f
    )
}