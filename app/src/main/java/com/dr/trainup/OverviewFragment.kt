package com.dr.trainup


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.data.entities.Exercise
import com.dr.trainup.databinding.FragmentOverviewBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentOverviewBinding

    private lateinit var viewModel: OverviewFragmentVM


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

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

        viewModel = ViewModelProviders.of(this, viewModelFactory)[OverviewFragmentVM::class.java]

        binding.exercises.layoutManager = LinearLayoutManager(requireContext())

        val observer = Observer<List<Exercise>> {
            handleExercisesLoaded(it)
        }
        viewModel.getExerciseData().observe(viewLifecycleOwner, observer)

        viewModel.loadExercises()

    }

    private fun handleExercisesLoaded(exerciseList: List<Exercise>) {
        binding.exercises.adapter = ExerciseOverviewAdapter(exerciseList)

    }

}
