package uz.direction.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.direction.news.Adapter.setImageFromUrl
import uz.direction.news.databinding.FragmentNewsContentBinding

class NewsContent : Fragment(R.layout.fragment_news_content) {
    private val binding by viewBinding(FragmentNewsContentBinding::bind)
    private val args: NewsContentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            newsAuthor.text = args.newsArticle.author
            newsDate.text = args.newsArticle.publishedAt
            newsDescription.text = args.newsArticle.description
            newsLink.text = args.newsArticle.url
            newsTitle.text = args.newsArticle.title
            newsImg.setImageFromUrl(args.newsArticle.urlToImage)
        }
    }
}