package uz.direction.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.FragmentNewsPageBinding
import uz.direction.news.Adapter.NewsAdapter
import uz.direction.news.data.model.Article
import uz.direction.news.data.repository.CountriesName

class News : Fragment(R.layout.fragment_news_page) {

    private val binding by viewBinding(FragmentNewsPageBinding::bind)
    private val repository = NewsRepository(RetrofitService.newsService)
    private val news: ArrayList<Article> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null)
            repository.getNews(CountriesName.US)

        repository.newsLiveData.observe(viewLifecycleOwner) { news ->
            val newsAdapter = NewsAdapter(news.articles) { position ->
                itemClicked(position)
            }
            binding.rvNews.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = newsAdapter
            }

        }
    }

    private fun itemClicked(position: Int) {
        val action = NewsDirections.actionMainPageToNewsContent(news[position])
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.cancelJob()
    }
}