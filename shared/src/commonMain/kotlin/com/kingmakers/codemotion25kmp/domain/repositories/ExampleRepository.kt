package com.kingmakers.codemotion25kmp.domain.repositories

import com.kingmakers.codemotion25kmp.domain.models.RandomUser

/**
 * Repository interface for authentication related operations.
 */
interface ExampleRepository {
    suspend fun getRandomUser(): Result<RandomUser>
}
