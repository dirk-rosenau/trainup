package com.dr.trainup.ui.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.data.entities.Station
import com.dr.trainup.ExerciseOverviewAdapter
import com.dr.trainup.R
import com.dr.trainup.databinding.FragmentOverviewBinding
import com.dr.trainup.ui.vm.OverviewFragmentVM
import dagger.android.support.AndroidSupportInjection
import de.trainup.common.ui.PrimaryActionModeCallback
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

        viewModel.stationData.observe(viewLifecycleOwner) {
            handleExercisesLoaded(it)
        }
        viewModel.loadExercises()

    }

    private fun onLongClickItem(stationId: Long) {
//        val bundle = Bundle()
//        bundle.putLong("id", stationId)
//        findNavController().navigate(R.id.trainingEditActivity, bundle)

        startActionMode()
    }

    private fun onClickItem(stationId: Long) {
        Toast.makeText(context, "Click: $stationId", Toast.LENGTH_LONG).show()
    }

    private fun handleExercisesLoaded(exerciseList: List<Station>) {
        binding.exercises.adapter =
            ExerciseOverviewAdapter(exerciseList, ::onClickItem, ::onLongClickItem)
    }

    private fun startActionMode() {
        activity?.startActionMode(
            PrimaryActionModeCallback(
                R.menu.menu_overview_actionmode,
                "",
                "",
                ::handleActionModeItemClicked
            )
        )
    }

    private fun handleActionModeItemClicked(id: Int) {
        when (id) {
            R.id.menu_edit -> Log.d("actionmode", "edit")
            R.id.menu_delete -> Log.d("actionmode", "delete")
        }
    }
}
