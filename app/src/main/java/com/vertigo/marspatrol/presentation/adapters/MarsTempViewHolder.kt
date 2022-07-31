package com.vertigo.marspatrol.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.vertigo.marspatrol.R
import com.vertigo.marspatrol.databinding.ItemMarsTempBinding
import com.vertigo.marspatrol.domain.model.MarsTemp

class MarsTempViewHolder(private val binding: ItemMarsTempBinding,
                         private val setMarsTempCallBack: ((MarsTemp) -> Unit)): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MarsTemp) {
        with(binding) {
            tempData.text = item.earth_date
            tempSol.text = item.sol
            // TODO: Вынести в строки
            tempMinCel.text = "${item.min_temp} °C"
            tempMaxCel.text = "${item.max_temp} °C"
            tempMinFar.text = "${getFahrenheitTemp(item.min_temp)} °F"
            tempMaxFar.text = "${getFahrenheitTemp(item.max_temp)} °F"

            when (item.atmo_opacity) {
                "Sunny" -> itemWeatherIcon.setImageResource(R.drawable.ic_sun_weather)
                else -> itemWeatherIcon.setImageResource(R.drawable.ic_sun_with_cloud)
            }
            itemView.setOnClickListener {
                setMarsTempCallBack.invoke(item)
            }
        }
    }

    private fun getFahrenheitTemp(temp: String) = (1.8 * temp.toInt() + 32).toInt()
}