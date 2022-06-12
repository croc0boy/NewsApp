package uz.direction.news.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.direction.news.data.network.news.NewsService

object RetrofitService {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.run {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader("Authorization", "c997789ef3ce4b7a965201cd2b06f002")
                        .build()
                )
            }
        }
        .build()

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://newsapi.org/")
            .build()
    val newsService = retrofit.create(NewsService::class.java)
}

