package agency.five.codebase.android.movieapp.mock

import kotlinx.coroutines.flow.MutableStateFlow

object FavoritesDBMock {
    val favoriteIds = MutableStateFlow(setOf(1,2,4))
    fun insert(movieId: Int){ favoriteIds.value += movieId }
    fun delete(movieId: Int){ favoriteIds.value -= (movieId) }
}
