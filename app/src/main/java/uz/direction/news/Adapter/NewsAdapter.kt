package uz.direction.news.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.direction.news.R
import uz.direction.news.data.model.Article

class NewsAdapter(
    private val articles: ArrayList<Article>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ArticleVH>() {

    class ArticleVH(
        view: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        private val newsImage = itemView.findViewById<ImageView>(R.id.news_img)
        private val newsTitle = itemView.findViewById<TextView>(R.id.news_title)
        private val newsAuthor = itemView.findViewById<TextView>(R.id.news_author)
        private val newsDate = itemView.findViewById<TextView>(R.id.news_date)
        private val newsDescription = itemView.findViewById<TextView>(R.id.news_description)
        fun onBind(
            article: Article,
        ) {
            newsTitle.text = article.title
            newsAuthor.text = article.author
            newsDate.text = article.publishedAt.subSequence(0, 10)
            newsDescription.text = article.description
            newsImage.setImageFromUrl(article.urlToImage)
        }

        override fun onClick(view: View?) {
            val position = bindingAdapterPosition
            onItemClicked(position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_prototype, parent, false)
        return ArticleVH(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ArticleVH, position: Int) {
        val article = articles[position]
        holder.onBind(article)
    }

    override fun getItemCount() = articles.size
}

fun ImageView.setImageFromUrl(urlToImage: String?) {
    Glide
        .with(this)
        .load(urlToImage)
        .placeholder(R.drawable.img)
        .into(this)
}