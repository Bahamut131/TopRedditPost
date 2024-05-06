package com.example.topredditpost.presentation.fragment

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.topredditpost.R
import com.example.topredditpost.TopPostApp
import com.example.topredditpost.databinding.FragmentShowImageBinding
import com.example.topredditpost.databinding.FragmentTopPostBinding
import com.example.topredditpost.presentation.viewModel.ShowImageViewModel
import com.example.topredditpost.presentation.viewModel.viewModelFactory.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ShowImageFragment : Fragment() {

    private var _binding: FragmentShowImageBinding? = null
    private val binding: FragmentShowImageBinding
        get() = _binding ?: throw RuntimeException("FragmentShowImageBinding == null")


    private val component by lazy {
        (requireActivity().application as TopPostApp).component
    }


    private val args by navArgs<ShowImageFragmentArgs>()

    @Inject
    lateinit var viewModelFactory : ViewModelFactory
    private val viewModel: ShowImageViewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[ShowImageViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowImageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fullImgUrl = args.image
        Log.d("Image", "$fullImgUrl")
        Picasso.get().load(fullImgUrl).into(binding.fullImage)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}