package com.example.topredditpost.domain.useCase

import com.example.topredditpost.domain.repository.TopPostRepository
import javax.inject.Inject

class ImageDownloadUseCase @Inject constructor(val repository: TopPostRepository) {
    suspend operator fun invoke(){
        repository.downloadImage()
    }
}