package com.example.oslik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oslik.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}