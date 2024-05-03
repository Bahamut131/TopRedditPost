package com.example.topredditpost.presentation.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.topredditpost.R
import com.example.topredditpost.presentation.viewModel.TopPostViewModel

class TopPostFragment : Fragment() {

    companion object {
        fun newInstance() = TopPostFragment()
    }

    private val viewModel: TopPostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_top_post, container, false)
    }
}