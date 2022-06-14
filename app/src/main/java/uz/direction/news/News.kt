package uz.direction.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.FragmentNewsPageBinding
import uz.direction.news.Adapter.NewsAdapter
import uz.direction.news.data.repository.CountriesName

class News : Fragment(R.layout.fragment_news_page) {

    private var binding: FragmentNewsPageBinding? = null
    private val repository = NewsRepository(RetrofitService.newsService)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null)
            repository.getNews(CountriesName.US)

        repository.newsLiveData.observe(viewLifecycleOwner) { news ->
            val newsAdapter = NewsAdapter(news.articles)
            binding?.rvNews?.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = newsAdapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsPageBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.cancelJob()
    }
}