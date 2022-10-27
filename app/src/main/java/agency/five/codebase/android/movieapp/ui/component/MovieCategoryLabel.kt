package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    movieCategoryLabelViewState: MovieCategoryLabelViewState
) {
    Column(
        modifier = Modifier
            .width(40.dp)
    ) {
        val isSelectedState = remember { mutableStateOf(movieCategoryLabelViewState.isSelected) }
        Text(
            text =
                when (movieCategoryLabelViewState.categoryText) {
                    is MovieCategoryLabelTextViewState.StringToText ->
                        movieCategoryLabelViewState.categoryText.text

                    is MovieCategoryLabelTextViewState.ResourceToText ->
                        stringResource(id = movieCategoryLabelViewState.categoryText.textRes)
                },
            color = if(isSelectedState.value) Color.Black else Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier
                .clickable {
                    isSelectedState.value = isSelectedState.value.not()
                }
                .fillMaxWidth()
        )

        if (isSelectedState.value) {
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
fun MovieCategoryLabelPreview(){
    MovieCategoryLabel(
        movieCategoryLabelViewState = MovieCategoryLabelViewState(
            itemId = 0,
            isSelected = true,
            categoryText = MovieCategoryLabelTextViewState.StringToText("Text")
        )
    )
}