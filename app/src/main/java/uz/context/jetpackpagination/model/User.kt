package uz.context.jetpackpagination.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String,
    @SerialName("bio")
    val bio: String = ""
)
