package uz.context.jetpackpagination.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.context.jetpackpagination.model.UnsplashImage
import uz.context.jetpackpagination.model.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 2)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}