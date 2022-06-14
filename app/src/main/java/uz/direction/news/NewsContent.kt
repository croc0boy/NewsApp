package uz.direction.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.direction.news.databinding.FragmentNewsPageBinding

class NewsContent : Fragment(R.layout.fragment_news_content) {
    private var binding: FragmentNewsPageBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsPageBinding.inflate(layoutInflater)
        return binding!!.root
    }
}