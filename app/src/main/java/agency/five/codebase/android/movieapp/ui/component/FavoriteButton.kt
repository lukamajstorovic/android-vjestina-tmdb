package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(
            id =
            if (!isFavorite) {
                R.drawable.empty_heart
            } else {
                R.drawable.full_heart
            }
        ),
        contentDescription = "Favorite button",
        modifier = Modifier
            .background(Color.Black.copy(0.7f), CircleShape)
            .clip(CircleShape)
            .clickable { onClick() }
            .then(modifier)
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    FavoriteButton(
        isFavorite = false,
        modifier = Modifier
            .size(30.dp)
            .padding(8.dp),
        onClick = { }
    )
}
