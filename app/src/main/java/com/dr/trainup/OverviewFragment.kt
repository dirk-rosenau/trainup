package com.dr.trainup


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.data.entities.Station
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
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        binding = FragmentOverviewBinding.bind(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[OverviewFragmentVM::class.java]

        binding.exercises.layoutManager = LinearLayoutManager(requireContext())

        val observer = Observer<List<Station>> {
            handleExercisesLoaded(it)
        }
        viewModel.getStationData().observe(viewLifecycleOwner, observer)

        viewModel.loadExercises()

    }

    private fun onLongClickItem(stationId: Long) {
//        val action = OverviewFragmentDirections.actionOverviewFragmentToTrainingEditor(stationId)
//        findNavController(
//            activity as Activity,
//            R.id.nav_fragment
//        ).navigate(action)
    }

    private fun onClickItem(stationId: Long) {
        Toast.makeText(context, "Click: $stationId", Toast.LENGTH_LONG).show()
    }

    private fun handleExercisesLoaded(exerciseList: List<Station>) {
        binding.exercises.adapter =
            ExerciseOverviewAdapter(exerciseList, ::onClickItem, ::onLongClickItem)

    }

}
