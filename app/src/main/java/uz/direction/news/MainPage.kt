package uz.direction.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.FragmentMainPageBinding
import uz.direction.news.recycleView.NewsAdapter

class MainPage : Fragment(R.layout.fragment_main_page) {

    private var binding: FragmentMainPageBinding? = null
    private val repository = NewsRepository(RetrofitService.newsService)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository.getNews()

        repository.newsLiveData.observe(viewLifecycleOwner) { news ->
            val newsAdapter = NewsAdapter(news.articles)
            binding?.rvNews?.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = newsAdapter
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        return binding!!.root

    }

    override fun onDestroy() {
        super.onDestroy()
        repository.cancelJob()
    }
}