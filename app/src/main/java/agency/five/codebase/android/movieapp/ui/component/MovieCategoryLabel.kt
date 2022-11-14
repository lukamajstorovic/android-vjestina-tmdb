package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

sealed class MovieCategoryLabelTextViewState {
    class StringToText(val text: String) : MovieCategoryLabelTextViewState()
    class ResourceToText(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Text(
            text =
            when (movieCategoryLabelViewState.categoryText) {
                is MovieCategoryLabelTextViewState.StringToText ->
                    movieCategoryLabelViewState.categoryText.text

                is MovieCategoryLabelTextViewState.ResourceToText ->
                    stringResource(id = movieCategoryLabelViewState.categoryText.textRes)
            },
            color = if (movieCategoryLabelViewState.isSelected) Color.Black else Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
        )

        if (movieCategoryLabelViewState.isSelected) {
            Spacer(
                modifier = Modifier
                    .size(2.dp)
            )
            Divider(
                thickness = 2.dp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCategoryLabelPreview() {
    MovieCategoryLabel(
        movieCategoryLabelViewState = MovieCategoryLabelViewState(
            itemId = 0,
            isSelected = true,
            categoryText = MovieCategoryLabelTextViewState.StringToText("Text")
        ),
        modifier = Modifier,
        onClick = { }
    )
}
