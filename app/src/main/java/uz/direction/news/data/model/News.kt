package uz.direction.news.data.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("articles")
    val articles: ArrayList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)
