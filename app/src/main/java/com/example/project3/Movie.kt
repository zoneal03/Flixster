package com.example.project3

import com.google.gson.annotations.SerializedName

class Movie {

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("backdrop_path")
    var movieImageUrl: String? = null

    @SerializedName("overview")
    var description: String? = null
}