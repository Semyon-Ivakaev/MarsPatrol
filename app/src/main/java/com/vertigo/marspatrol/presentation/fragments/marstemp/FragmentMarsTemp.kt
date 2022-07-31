package com.vertigo.marspatrol.presentation.fragments.marstemp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.marspatrol.R
import com.vertigo.marspatrol.databinding.ActivityMainBinding
import com.vertigo.marspatrol.databinding.FragmentTempBinding
import com.vertigo.marspatrol.domain.model.MarsTemp
import com.vertigo.marspatrol.presentation.adapters.MarsTempAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMarsTemp: Fragment() {

    private var _binding: FragmentTempBinding? = null
    private val binding get() = _binding!!

    private val marsTempViewModel: MarsTempViewModel by viewModels()

    private lateinit var marsTempAdapter: MarsTempAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("App", "Temp")
        marsTempViewModel.getMarsTempList()
        _binding = FragmentTempBinding.inflate(inflater, container, false)
        createRecycler()

        marsTempViewModel.marsTempList.observe(viewLifecycleOwner, {
            result ->
            updateRecycler(updateTempList = result)
        })

        marsTempViewModel.marsMainTemp.observe(viewLifecycleOwner, {
            result -> setViews(data = result)
        })

        return binding.root
    }

    private fun setViews(data: MarsTemp?) {
        with(binding) {
            weatherDate.text = data?.earth_date?.replace("-", ".")
            weatherSol.text = data?.sol
            weatherAirMinValue.text = data?.min_temp
            weatherAirMaxValue.text = data?.max_temp
            weatherGroundMinValue.text = data?.min_gts_temp
            weatherGroundMaxValue.text = data?.max_gts_temp
            weatherOpacityIcon.setImageResource(R.drawable.ic_sun_weather)
            weatherOpacityText.text = data?.atmo_opacity
            weatherSunriseValue.text = data?.sunrise
            weatherSunsetValue.text = data?.sunset
        }
    }

    private fun createRecycler() {
        marsTempAdapter = MarsTempAdapter(setMarsTempCallBack = fun(temp: MarsTemp) {
            marsTempViewModel.setMarsMainTemp(temp)
        })
        layoutManager = GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, false)

        with(binding) {
            tempRecycler.adapter = marsTempAdapter
            tempRecycler.layoutManager = layoutManager
            tempRecycler.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun updateRecycler(updateTempList: List<MarsTemp>?) {
        marsTempAdapter.submitList(updateTempList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}