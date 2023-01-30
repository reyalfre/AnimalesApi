package com.alfredomaldonado.animalesapi.server

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getAnimalByBreeds(@Url url: String): Response<AnimalResponse>
}