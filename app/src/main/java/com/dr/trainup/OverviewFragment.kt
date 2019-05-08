package com.dr.trainup


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.data.entities.Exercise
import com.dr.trainup.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {

    lateinit var binding: FragmentOverviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.exercises.layoutManager = LinearLayoutManager(requireContext())

        // TODO get from repo, map to view item
        val exerciseList = listOf(
            Exercise(0, "Bankpflücken", null),
            Exercise(1, "Liegestütze", null),
            Exercise(2, "Situps", null)
        )
        binding.exercises.adapter = ExerciseOverviewAdapter(exerciseList)
    }

}
