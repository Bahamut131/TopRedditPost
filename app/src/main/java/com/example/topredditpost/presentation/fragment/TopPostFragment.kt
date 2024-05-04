package com.example.topredditpost.presentation.fragment

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topredditpost.R
import com.example.topredditpost.TopPostApp
import com.example.topredditpost.databinding.FragmentTopPostBinding
import com.example.topredditpost.presentation.recyclerView.TopPostAdapter
import com.example.topredditpost.presentation.viewModel.TopPostViewModel
import com.example.topredditpost.presentation.viewModel.viewModelFactory.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopPostFragment : Fragment() {

    private var _binding: FragmentTopPostBinding? = null
    private val binding: FragmentTopPostBinding
        get() = _binding ?: throw RuntimeException("TopPostFragment == null")

    private var topPostAdapter = TopPostAdapter()

    private val layoutManager by lazy {
        LinearLayoutManager(requireActivity())
    }


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: TopPostViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[TopPostViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as TopPostApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchRecyclerView()
        launchFlow()
    }


    private fun launchFlow() {
        lifecycleScope.launch {
            viewModel.list.observe(viewLifecycleOwner) {
                topPostAdapter.submitList(it)
            }
        }
    }

    private fun launchRecyclerView() {
        topPostAdapter = TopPostAdapter()
        binding.recyclerTopPost.layoutManager = layoutManager
        binding.recyclerTopPost.adapter = topPostAdapter
        binding.recyclerTopPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val postsCount = visibleItemCount + firstVisibleItemPosition

                if (postsCount >= totalItemCount) {
                    viewModel.loadMorePosts()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = TopPostFragment()
    }

}