package com.example.topredditpost.presentation.fragment

import android.Manifest
import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topredditpost.R
import com.example.topredditpost.TopPostApp
import com.example.topredditpost.databinding.FragmentTopPostBinding
import com.example.topredditpost.presentation.recyclerView.TopPostAdapter
import com.example.topredditpost.presentation.viewModel.TopPostViewModel
import com.example.topredditpost.presentation.viewModel.viewModelFactory.ViewModelFactory
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class TopPostFragment : Fragment() {

    private var _binding: FragmentTopPostBinding? = null
    private val binding: FragmentTopPostBinding
        get() = _binding ?: throw RuntimeException("TopPostFragment == null")

    private var topPostAdapter = TopPostAdapter()

    private var urlFromViewModel : String ?=null



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
        launchViewModel()
    }


    private fun launchViewModel() {
        lifecycleScope.launch {
            viewModel.list.observe(viewLifecycleOwner) {
                topPostAdapter.submitList(it)
            }
        }
    }



    private fun launchRecyclerView() {
        val layoutManager = LinearLayoutManager(requireActivity())

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
        launchMenuDonwload()
        launchOnClickImg()
    }

    private fun launchOnClickImg(){
        topPostAdapter.onPostImgClickListener = {
            findNavController().navigate(TopPostFragmentDirections.actionTopPostFragmentToShowImageFragment(it!!))
        }
    }

    private fun launchMenuDonwload(){
        topPostAdapter.onMenuClickListener = { view, url ->
            val popupMenu = PopupMenu(context, view)
            urlFromViewModel = url
            popupMenu.menuInflater.inflate(R.menu.item_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_download_image -> {
                        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                            viewModel.downloadImage(url!!)
                        }else{
                            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),REQUEST_CODE)
                        }
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            launchMenuDonwload()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val REQUEST_CODE = 0
    }

}