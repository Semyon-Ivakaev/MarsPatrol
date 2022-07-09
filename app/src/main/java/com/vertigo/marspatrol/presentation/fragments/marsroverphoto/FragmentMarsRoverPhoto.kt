package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.appcompat.widget.CustomPopupMenu
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.marspatrol.R
import com.vertigo.marspatrol.databinding.FragmentMarsRoverBinding
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.presentation.adapters.MarsRoverPhotoAdapter

class FragmentMarsRoverPhoto: Fragment() {
    private var _binding: FragmentMarsRoverBinding? = null
    private val binding get() = _binding!!

    private lateinit var marsRoverPhotoViewModel: MarsRoverPhotoViewModel
    private lateinit var marsRoverPhotoAdapter: MarsRoverPhotoAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsRoverBinding.inflate(inflater, container, false)
        val view = binding.root

        initViews()
        createRecycler()

        marsRoverPhotoViewModel = ViewModelProvider(this, MarsRoverViewModelFactory())[MarsRoverPhotoViewModel::class.java]

        marsRoverPhotoViewModel.marsRoverPhotos.observe(viewLifecycleOwner, {
            result ->
                updateRecycler(result)
                showProgressBar(false)
        })
        return view
    }

    private fun createRecycler() {
        marsRoverPhotoAdapter = MarsRoverPhotoAdapter()
        layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        with(binding) {
            photoRecycler.adapter = marsRoverPhotoAdapter
            photoRecycler.layoutManager = layoutManager
            photoRecycler.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun updateRecycler(updatePhotoList: List<MarsPhoto>?) {
        marsRoverPhotoAdapter = MarsRoverPhotoAdapter()
        marsRoverPhotoAdapter.submitList(updatePhotoList)
        with(binding) {
            photoRecycler.adapter = marsRoverPhotoAdapter
            photoRecycler.layoutManager = layoutManager
            photoRecycler.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun initViews() {
        with(binding) {
            filterButton.setOnClickListener {
                showPopup(filterButton)
            }
        }
    }

    private fun showPopup(filterIcon: View) {
        val popup = CustomPopupMenu(requireContext(), filterIcon)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.filter_menu, popup.menu)

        setIconMenu(popup)

        popup.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.Curiosity -> {
                    marsRoverPhotoViewModel.getNeededMarsPhotoList("Curiosity")
                    showProgressBar(show = true)
                    clearRecycler()
                    setTitle("Curiosity")
                    marsRoverPhotoViewModel.setMarsRoverTitle("Curiosity")
                }
                else -> {
                    marsRoverPhotoViewModel.getNeededMarsPhotoList("Perseverance")
                    showProgressBar(show = true)
                    clearRecycler()
                    setTitle("Perseverance")
                    marsRoverPhotoViewModel.setMarsRoverTitle("Perseverance")
                    popup.menu.findItem(R.id.Perseverance).setIcon(R.drawable.ic_rover_true)
                    popup.menu.findItem(R.id.Curiosity).setIcon(R.drawable.ic_rover_false)
                }
            }
            true
        }
        popup.show()
    }

    private fun setIconMenu(popup: CustomPopupMenu) {
        if (marsRoverPhotoViewModel.marsRoverTitle.value == "Curiosity") {
            popup.menu.findItem(R.id.Perseverance).setIcon(R.drawable.ic_rover_false)
            popup.menu.findItem(R.id.Curiosity).setIcon(R.drawable.ic_rover_true)
        } else {
            popup.menu.findItem(R.id.Perseverance).setIcon(R.drawable.ic_rover_true)
            popup.menu.findItem(R.id.Curiosity).setIcon(R.drawable.ic_rover_false)
        }
    }

    private fun setTitle(roverName: String) {
        with(binding) {
            roverNameTitle.text = roverName
        }
    }

    private fun showProgressBar(show: Boolean) {
        with(binding) {
            progressBar.isVisible = show
        }
    }

    private fun clearRecycler() {
        with(binding) {
            photoRecycler.layoutManager = null
        }
    }
}