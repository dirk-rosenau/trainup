package com.dr.trainup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dr.data.entities.Exercise
import com.dr.trainup.databinding.ItemExcerciseBinding

class ExerciseOverviewAdapter(private val exercises: List<Exercise>) :
    RecyclerView.Adapter<ExerciseOverviewAdapter.ExerciseViewHolder>() {
    override fun getItemCount(): Int = exercises.size


    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.binding.viewModel = ExerciseOverviewItemViewModel(exercises[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_excercise, parent, false)
        return ExerciseViewHolder(view)
    }


    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemExcerciseBinding.bind(itemView)
    }
}
