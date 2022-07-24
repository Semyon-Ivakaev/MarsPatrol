package com.vertigo.marspatrol.presentation.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.vertigo.marspatrol.databinding.FragmentDetailsBinding
import com.vertigo.marspatrol.domain.model.MarsPhoto

class FragmentDetails: Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: FragmentDetailsArgs by navArgs()

    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val photo = args.photo

        detailsViewModel = DetailsViewModel()

        detailsViewModel.setDetailPhoto(photo = photo)

        detailsViewModel.detailViewModel.observe(viewLifecycleOwner, {
            photo -> setViews(photo = photo)
        })

        with(binding) {
            swipeRefresh.setOnRefreshListener {
                setViews(photo = photo)
                swipeRefresh.isRefreshing = false
            }
        }

        return view
    }

    private fun setViews(photo: MarsPhoto) {
        with(binding) {
            loadPhoto(photo.img_url, mainPhoto)
            fullName.text = photo.camera_name
            onePhotoDate.text = photo.earth_date
            sol.text = photo.sol
            author.text = photo.rover_name
        }
    }

    private fun loadPhoto(url: String, imageView: ImageView) {
        Glide.with(requireContext())
            .load(url)
            .skipMemoryCache(true)
            .centerCrop()
            .into(imageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}