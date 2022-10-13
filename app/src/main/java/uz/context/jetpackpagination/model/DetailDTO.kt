package uz.context.jetpackpagination.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DetailDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("user")
    val user: User
)