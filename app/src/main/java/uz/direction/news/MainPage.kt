package uz.direction.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.ActivityMainBinding
import uz.direction.news.databinding.FragmentMainPageBinding


class MainPage : Fragment(R.layout.fragment_main_page) {

    private var binding: FragmentMainPageBinding? = null
    private val repository = NewsRepository(RetrofitService.newsService)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository.getNews()

        repository.newsLiveData.observe(viewLifecycleOwner) { news ->
            binding?.newsContent?.text = news.articles[2].content
            Log.d("TAG", news.articles[2].author )
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