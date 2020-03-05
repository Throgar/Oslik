package com.example.oslik.models

import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.util.logging.Logger

class DataModel {

    private val Log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
    private val URL = "http://www.osel.cz/"

    fun loadArticles() {
        Log.info("Loading articles from: $URL")

        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch {

            val doc = withContext(Dispatchers.IO) {
                    Jsoup.connect(URL).get()
                }

            Log.info("title -> ${doc.title()}")

            val elements = doc.getElementsByClass("clanek_uvodka_new")

            elements.forEach {
                Log.info("element: $it")
            }
        }


    }
}


