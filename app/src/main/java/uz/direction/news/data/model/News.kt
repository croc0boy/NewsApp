package uz.direction.news.data.model

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import uz.direction.news.data.network.news.NewsService
import java.sql.ClientInfoStatus

data class News(
    @SerializedName("articles")
    val articles: ArrayList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int

)
