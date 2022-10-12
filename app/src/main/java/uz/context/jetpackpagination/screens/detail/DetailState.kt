package uz.context.jetpackpagination.screens.detail

import uz.context.jetpackpagination.model.Detail

data class DetailState(
    val detail: Detail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)