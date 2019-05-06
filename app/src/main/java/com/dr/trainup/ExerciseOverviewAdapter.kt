package com.dr.trainup

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dr.data.entities.Exercise

class ExerciseOverviewAdapter(private val exercises: List<Exercise>) :
    RecyclerView.Adapter<ExerciseOverviewAdapter.ExerciseViewHolder>() {
    override fun getItemCount(): Int = exercises.size


    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ExerciseViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val binding = ColorItemBinding.bind(itemView)
    }
}
