package uz.direction.news.recycleView

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

    ) : RecyclerView.Adapter<NewsAdapter.ArticleVH>() {
    private var onClick: ((Article, Int) -> Unit)? = null

    fun setOnClickListener(clickEvent: ((Article, Int) -> Unit)) {
        onClick = clickEvent
    }

    class ArticleVH(view: View) :
        RecyclerView.ViewHolder(view) {
        private val newsImage = itemView.findViewById<ImageView>(R.id.news_img)
        private val newsTitle = itemView.findViewById<TextView>(R.id.news_title)
        private val newsAuthor = itemView.findViewById<TextView>(R.id.news_author)
        private val newsDate = itemView.findViewById<TextView>(R.id.news_date)
        private val newsDescription = itemView.findViewById<TextView>(R.id.news_description)
        fun onBind(
            article: Article,
            position: Int,
            onClick: (Article, Int) -> Unit
        ) {
            newsTitle.text = article.title
            newsAuthor.text = article.author
            newsDate.text = article.publishedAt.subSequence(0,10)
            newsDescription.text = article.description
            Glide.with(itemView.context).load(article.urlToImage).into(newsImage)
            itemView.setOnClickListener {
                onClick.invoke(article, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_prototype, parent, false)
        return ArticleVH(view)
    }

    override fun onBindViewHolder(holder: ArticleVH, position: Int) {
        val article = articles[position]
        holder.onBind(article, position, onClick ?: { article, i -> })

    }

    override fun getItemCount() = articles.size
}