package com.dr.trainup.statistics.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dr.trainup.statistics.R
import com.dr.trainup.statistics.databinding.ItemDateGroupBinding
import com.dr.trainup.statistics.databinding.ItemStationBinding
import com.dr.trainup.statistics.ui.model.DateGroupable
import com.dr.trainup.statistics.ui.model.Groupable
import com.dr.trainup.statistics.ui.model.items.DateItemData
import com.dr.trainup.statistics.ui.model.items.StationItemData
import com.dr.trainup.statistics.vm.DateGroupVM
import com.dr.trainup.statistics.vm.StationItemVM
import com.dr.trainup.statistics.vm.StatisticsIntent

class StatisticsAdapter(private val onIntent: (StatisticsIntent) -> Unit, private val data: List<Groupable>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val internalItemList = mutableListOf<Groupable>()

    companion object {
        const val VIEW_TYPE_DATE = 1
        const val VIEW_TYPE_STATION = 2

    }

    init {
        unfoldList()
    }

    private fun unfoldList() {
        data.forEach {
            internalItemList.add(it)
            it.getChildren()?.let { children -> internalItemList.addAll(children) }
        }
    }

    override fun getItemCount(): Int = internalItemList.size

    override fun getItemViewType(position: Int): Int {
        return when (internalItemList[position]) {
            is DateGroupable -> VIEW_TYPE_DATE
            else -> VIEW_TYPE_STATION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_DATE) {
            val view = inflater.inflate(R.layout.item_date_group, parent, false)
            return DateGroupViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_station, parent, false)
            return StationViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemData = internalItemList[position].getItemContainer()
        if (holder is DateGroupViewHolder) {
            holder.binding.vm = DateGroupVM(itemData as DateItemData)
        } else if (holder is StationViewHolder) {
            holder.binding.vm = StationItemVM(itemData as StationItemData, onIntent)
        }
    }
}


class DateGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ItemDateGroupBinding = ItemDateGroupBinding.bind(itemView)
}

class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ItemStationBinding = ItemStationBinding.bind(itemView)
}
