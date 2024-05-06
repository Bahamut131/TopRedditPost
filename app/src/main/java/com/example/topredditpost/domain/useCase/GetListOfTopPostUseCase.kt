package com.example.topredditpost.domain.useCase

import com.example.topredditpost.domain.repository.TopPostRepository
import javax.inject.Inject

class GetListOfTopPostUseCase @Inject constructor(val repository: TopPostRepository) {
    operator fun invoke() = repository.getListOfTopPost()
}