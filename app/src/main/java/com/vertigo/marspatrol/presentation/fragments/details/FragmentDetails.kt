package com.vertigo.marspatrol.presentation.fragments.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.vertigo.marspatrol.databinding.FragmentDetailsBinding
import com.vertigo.marspatrol.domain.model.MarsPhoto
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class FragmentDetails: Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: FragmentDetailsArgs by navArgs()

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val photo = args.photo

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
            onePhotoTitle.text = photo.rover_name
            fullName.text = photo.camera_name
            onePhotoDate.text = photo.earth_date
            sol.text = "Sol: ${photo.sol}"
            author.text = photo.rover_name

            share.setOnClickListener {
                sharePhoto(photo = photo)
            }

            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            titleStar.setOnClickListener {
                detailsViewModel.actionWithPhoto()
            }
        }
    }

    private fun loadPhoto(url: String, imageView: ImageView) {
        Glide.with(requireContext())
            .load(url)
            .skipMemoryCache(true)
            .centerCrop()
            .into(imageView)
    }

    private fun sharePhoto(photo: MarsPhoto?) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,
                photo?.rover_name + " " +  photo?.earth_date + " sol: " + photo?.sol
                        + "\n" + photo?.img_url
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, null))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}