package com.csosa.healiostest.adapters

import android.view.View
import com.csosa.healiostest.databinding.ItemForecastDayBinding
import com.csosa.healiostest.models.ForecastDayPresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder


internal class ForecastDayViewHolder(
    view: View
) : RecyclerViewHolder<ForecastDayPresentation>(view) {

    val binding = ItemForecastDayBinding.bind(view)
    override fun bind(position: Int, item: ForecastDayPresentation) {

        super.bind(position, item)
        binding.executePendingBindings()
    }
}
