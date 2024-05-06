package com.example.topredditpost.data.repository

import android.app.Application
import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.example.topredditpost.data.mapper.TopPostMapper
import com.example.topredditpost.data.network.ApiService
import com.example.topredditpost.data.network.model.JsonTopPost
import com.example.topredditpost.domain.entity.Post
import com.example.topredditpost.domain.repository.TopPostRepository
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject

class TopPostRepositoryImpl @Inject constructor(
    val mapper: TopPostMapper,
    val apiService: ApiService,
    val application: Application
) : TopPostRepository {

    private var _after : String =""
    private val _listOfPosts = mutableListOf<Post>()

    override fun getListOfTopPost():Flow<List<Post>>  = flow{
        val listDto = apiService.getListOfTopPost(10,_after)
        _after = listDto.data?.after.toString()
        val listDataX = mapper.takeDataXFromJson(listDto)
        val listTopPost = listDataX.map { mapper.mapDtoToEntity(it) }

        _listOfPosts.addAll(listTopPost)
        emit(_listOfPosts.toList())
    }



    override suspend fun downloadImage(url : String) {
        Picasso.get().load(url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap?.let {
                    saveBitmapToGallery(it)
                }
            }
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })
    }

    private fun saveBitmapToGallery(bitmap: Bitmap) {
        val displayName = "Image_${System.currentTimeMillis()}.jpg"
        val imageUri = saveImageUri(bitmap, displayName)
        notifyGalleryAboutNewImage(imageUri)
    }

    private fun saveImageUri(bitmap: Bitmap, displayName: String): Uri? {
        val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val imageFile = File(imagesDir, displayName)
        val imageUri = Uri.fromFile(imageFile)

        try {
            val outputStream: OutputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            return imageUri
        } catch (e: Exception) {
            throw RuntimeException("cant download image in TopPostRepositoryImpl -> method saveImageUri")
        }

        return null
    }

    private fun notifyGalleryAboutNewImage(imageUri: Uri?) {
        imageUri?.let {
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            mediaScanIntent.data = it
            application.sendBroadcast(mediaScanIntent)
        }
    }

}