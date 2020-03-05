package com.example.oslik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oslik.databinding.FragmentArticleListBinding
import com.example.oslik.models.DataModel
import java.util.logging.Logger

class ArticleListFragment : Fragment() {

    private val Log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleListBinding.inflate(inflater, container, false)
        binding.articleFragment = this

        DataModel().loadArticles()

        return binding.root
    }

    fun onButtonHardClick(view: View) {
        Log.info("DRAGON onClick() event has been registered")
        Navigation.findNavController(view)
            .navigate(R.id.action_article_list_fragment_to_articleDetailFragment)
    }
}