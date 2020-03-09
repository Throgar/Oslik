package com.example.oslik.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oslik.data.ArticleThumbnail
import com.example.oslik.models.DataModel

class ArticleListViewModel(val context: Context) : ViewModel(){

    private val model = DataModel.getInstance()

    fun articleThumbnailList() : MutableLiveData<List<ArticleThumbnail>>{
        return model.articleThumbnail
    }

    fun loadArticles() {
        model.loadArticles()
    }
}