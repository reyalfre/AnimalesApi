package com.alfredomaldonado.animalesapi.server

import com.google.gson.annotations.SerializedName

data class AnimalResponse(

    @SerializedName("message") val imagen: List<String>,
    val status: String
)
