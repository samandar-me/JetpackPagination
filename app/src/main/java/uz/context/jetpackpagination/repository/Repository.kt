package uz.context.jetpackpagination.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.context.jetpackpagination.database.UnsplashDatabase
import uz.context.jetpackpagination.model.Detail
import uz.context.jetpackpagination.model.UnsplashImage
import uz.context.jetpackpagination.paging.SearchPagingSource
import uz.context.jetpackpagination.paging.UnsplashRemoteMediator
import uz.context.jetpackpagination.remote.UnsplashApi
import uz.context.jetpackpagination.util.Constants.ITEMS_PER_PAGE
import uz.context.jetpackpagination.util.toDetail
import javax.inject.Inject

class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) {
    @ExperimentalPagingApi
    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unsplashApi, query = query)
            }
        ).flow
    }

    suspend fun getImageById(id: String): Flow<Detail> {
        return flow {
            emit(unsplashApi.getPhotoById(id).toDetail())
        }
    }
}