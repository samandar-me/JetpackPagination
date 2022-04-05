package uz.context.jetpackpagination.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.context.jetpackpagination.util.Constants.UNSPLASH_REMOTE_TABLE

@Entity(tableName = UNSPLASH_REMOTE_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
