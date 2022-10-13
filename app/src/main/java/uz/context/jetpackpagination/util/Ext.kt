package uz.context.jetpackpagination.util

import uz.context.jetpackpagination.model.Detail
import uz.context.jetpackpagination.model.DetailDTO

fun DetailDTO.toDetail(): Detail {
    return Detail(
        id = id,
        url = urls.regular,
        likes = likes,
        userName = user.username,
        color = color,
        bio = user.bio
    )
}