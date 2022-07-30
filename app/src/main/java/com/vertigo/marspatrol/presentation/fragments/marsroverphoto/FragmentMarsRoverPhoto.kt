package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.CustomPopupMenu
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
import com.vertigo.marspatrol.presentation.App
import com.vertigo.marspatrol.presentation.adapters.MarsRoverPhotoAdapter

class FragmentMarsRoverPhoto: Fragment() {
    private var _binding: FragmentMarsRoverBinding? = null
    private val binding get() = _binding!!

    private lateinit var marsRoverPhotoViewModel: MarsRoverPhotoViewModel
    private lateinit var marsRoverPhotoAdapter: MarsRoverPhotoAdapter
    private lateinit var layoutManager: LinearLayoutManager

    // DatePickerDialog
    private var dateDialogFragment: DatePickerDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsRoverBinding.inflate(inflater, container, false)
        val view = binding.root

        initViews()
        createRecycler()
        initPullToRefresh()

        marsRoverPhotoViewModel = ViewModelProvider(this, MarsRoverViewModelFactory())[MarsRoverPhotoViewModel::class.java]

        marsRoverPhotoViewModel.marsRoverTitle.observe(viewLifecycleOwner, {
            title -> setTitle(title)
        })

        marsRoverPhotoViewModel.marsRoverPhotos.observe(viewLifecycleOwner, {
            result ->
            if (result?.isNotEmpty() == true) {
                updateRecycler(result)
                showProgressBar(show = false)
                showErrorMessage(show = false, null)
            }
        })

        marsRoverPhotoViewModel.marsRoverDate.observe(viewLifecycleOwner, {
            marsRoverPhotoViewModel.getNeededMarsPhotoList()
        })

        marsRoverPhotoViewModel.errorConnectionMessage.observe(viewLifecycleOwner, {
            result ->
                showErrorMessage(show = result, requireContext().getString(R.string.internet_connection_message))
                clearRecycler()
                showProgressBar(show = result)
        })

        marsRoverPhotoViewModel.emptyListMessage.observe(viewLifecycleOwner, {
            result ->
                showErrorMessage(show = result, requireContext().getString(R.string.empty_list_message))
                clearRecycler()
                showProgressBar(show = result)
        })

        return view
    }

    private fun createRecycler() {
        marsRoverPhotoAdapter = MarsRoverPhotoAdapter(fragmentType = App.MARS_ROVER_TYPE)
        layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        with(binding) {
            photoRecycler.adapter = marsRoverPhotoAdapter
            photoRecycler.layoutManager = layoutManager
            photoRecycler.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun updateRecycler(updatePhotoList: List<MarsPhoto>?) {
        marsRoverPhotoAdapter = MarsRoverPhotoAdapter(fragmentType = App.MARS_ROVER_TYPE)
        marsRoverPhotoAdapter.submitList(updatePhotoList)
        with(binding) {
            photoRecycler.adapter = marsRoverPhotoAdapter
            photoRecycler.layoutManager = layoutManager
            photoRecycler.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun clearRecycler() {
        with(binding) {
            photoRecycler.layoutManager = null
        }
    }

    private fun initViews() {
        with(binding) {
            filterButton.setOnClickListener {
                showPopup(filterButton)
            }
            calendarButton.setOnClickListener {
                showDatePickerDialog()
            }
        }
    }

    private fun initPullToRefresh() {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                clearRecycler()
                showProgressBar(show = true)
                marsRoverPhotoViewModel.getNeededMarsPhotoList()
                swipeRefresh.isRefreshing = false
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
                    marsRoverPhotoViewModel.setMarsRoverTitle(title = App.NAME_CURIOSITY)
                    marsRoverPhotoViewModel.getNeededMarsPhotoList()
                    showProgressBar(show = true)
                    clearRecycler()
                }
                else -> {
                    marsRoverPhotoViewModel.setMarsRoverTitle(title = App.NAME_PERSEVERANCE)
                    marsRoverPhotoViewModel.getNeededMarsPhotoList()
                    showProgressBar(show = true)
                    clearRecycler()
                }
            }
            true
        }
        popup.show()
    }

    private fun setIconMenu(popup: CustomPopupMenu) {
        if (marsRoverPhotoViewModel.marsRoverTitle.value == App.NAME_CURIOSITY) {
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

    private fun showErrorMessage(show: Boolean, messageText: String?) {
        with(binding) {
            errorMessageTextview.text = messageText
            errorMessageCardview.isVisible = show
        }
    }

    private fun showProgressBar(show: Boolean) {
        Log.v("App", "showProgressBar $show")
        with(binding) {
            progressBar.isVisible = show
        }
    }

    /**
     * Дата для календаря теперь хранится и перезаписывается во ViewModel и при открытии DatePickerDialog
     * сразу заполняется выбранным числом, либо числом по умолчанию при первичном открытии (сегодняшний день)
     */
    private fun showDatePickerDialog() {
        marsRoverPhotoViewModel.marsRoverDate.observe(viewLifecycleOwner, {
            result ->
                dateDialogFragment = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener{
                        _, mYear, mMonth, mDay ->
                    marsRoverPhotoViewModel.setDateForCalendar(day = mDay, month = mMonth, year = mYear)
                    showProgressBar(show = true)
                }, result.year.toInt(), result.month.toInt() - 1, result.day.toInt())

        })
        dateDialogFragment?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}