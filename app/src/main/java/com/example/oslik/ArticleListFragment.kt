package com.example.oslik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.example.oslik.adapters.ArticleListAdapter
import com.example.oslik.databinding.FragmentArticleListBinding
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
        context ?: binding.root

        viewModel.loadArticles()
        binding.articleFragment = this

        val adapter = ArticleListAdapter()
        binding.articleRecycler.adapter = adapter
        subscribeData(adapter)

        return binding.root
    }

    fun onButtonHardClick(view: View) {
        Log.info("DRAGON onClick() event has been registered")
        Navigation.findNavController(view)
            .navigate(R.id.action_article_list_fragment_to_articleDetailFragment)
    }

    private fun subscribeData(adapter: ArticleListAdapter) {
        viewModel.articleThumbnailList().observe(viewLifecycleOwner) {articles ->
            adapter.submitList(articles)
        }
    }
}