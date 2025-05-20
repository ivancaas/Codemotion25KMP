package com.kingmakers.codemotion25kmp.data.api

import com.kingmakers.codemotion25kmp.data.models.RandomUserDTO
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface ExampleApi {
    @GET("{baseUrl}")
    @NativeCoroutinesIgnore
    suspend fun getRandomUser(
        @Path baseUrl: String = "https://randomuser.me/api",
        @Query("nat") nationality: String = "es",
    ): Response<RandomUserDTO>
}