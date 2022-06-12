package uz.direction.news.data.network.news

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.direction.news.data.model.News

interface NewsService {
    @GET("v2/top-headlines")
    suspend fun getRandomNews(@Query("country") country: String): Response<News>
}