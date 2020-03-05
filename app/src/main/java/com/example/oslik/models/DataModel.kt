package com.example.oslik.models

import androidx.lifecycle.MutableLiveData
import com.example.oslik.data.ArticleThumbnail
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.util.logging.Logger

class DataModel {

    private val Log = Logger.getLogger(this::class.java.toString())
    private val URL = "http://www.osel.cz/"

    val articleThumbnail: MutableLiveData<List<ArticleThumbnail>> = MutableLiveData()

    fun loadArticles() {
        Log.info("Loading articles from: $URL")

        val articles : MutableList<ArticleThumbnail> = ArrayList()

        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch {

            val doc = withContext(Dispatchers.IO) {
                    Jsoup.connect(URL).get()
                }

            val elements = doc.getElementsByClass("clanek_uvodka_new")
            Log.info("Elemets count: ${elements.size}")

            elements.forEach {
                val articleHeader = it.getElementsByClass("nadpis_clanku").first()
                    .getElementsByTag("a").first()
                    .attributes()

                articles.add( ArticleThumbnail(
                    it.getElementsByTag("img").first().attributes().get("src"),
                    articleHeader.get("title"),
                    it.getElementsByClass("popisek").first().ownText(),
                    it.getElementsByClass("td_author_1").first().getElementsByTag("a").first().ownText(),
                    articleHeader.get("href")
                    )
                )
            }

            articles.forEach {
                Log.info("Articles: $it")
            }
        }

        articleThumbnail.value = articles
    }
}


