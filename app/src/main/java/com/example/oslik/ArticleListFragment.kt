package com.example.oslik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.example.oslik.databinding.FragmentArticleListBinding
import com.example.oslik.models.DataModel
import com.example.oslik.viewmodels.ArticleListViewModel
import com.example.oslik.viewmodels.ArticleListViewModelFactory
import java.util.logging.Logger

class ArticleListFragment : Fragment() {

    private val Log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
    private val viewModel : ArticleListViewModel by lazy {
        ViewModelProviders.of(this, ArticleListViewModelFactory(requireContext()))
            .get(ArticleListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleListBinding.inflate(inflater, container, false)
        binding.articleFragment = this

        viewModel.loadArticles()

        viewModel.articleThumbnailList().observe(viewLifecycleOwner, Observer { list ->
            list?.forEach {
                Log.info("Articles: $it")
            }
        })

        return binding.root
    }

    fun onButtonHardClick(view: View) {
        Log.info("DRAGON onClick() event has been registered")
        Navigation.findNavController(view)
            .navigate(R.id.action_article_list_fragment_to_articleDetailFragment)
    }
}