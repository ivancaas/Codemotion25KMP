package com.kingmakers.codemotion25kmp.data.repositories

import com.kingmakers.codemotion25kmp.data.api.ExampleApi
import com.kingmakers.codemotion25kmp.domain.models.RandomUser
import com.kingmakers.codemotion25kmp.domain.models.toDomain
import com.kingmakers.codemotion25kmp.domain.repositories.ExampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class ExampleRepositoryImpl(
    private val exampleApi: ExampleApi
) : ExampleRepository {
    override suspend fun getRandomUser(): Result<RandomUser> {
        return withContext(Dispatchers.IO) {
            Result.success(exampleApi.getRandomUser().body()?.toDomain() ?: RandomUser(emptyList()))
        }
    }
}