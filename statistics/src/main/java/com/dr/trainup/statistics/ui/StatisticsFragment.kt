package com.dr.trainup.statistics.ui


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.trainup.statistics.R
import com.dr.trainup.statistics.databinding.FragmentStatisticsBinding
import com.dr.trainup.statistics.ui.model.Groupable
import com.dr.trainup.statistics.vm.SelectExerciseIntent
import com.dr.trainup.statistics.vm.StatisticsIntent
import com.dr.trainup.statistics.vm.StatisticsOverviewVM
import com.trainup.common.extensions.convertToLocalDateString
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class StatisticsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: StatisticsOverviewVM

    private lateinit var binding: FragmentStatisticsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this, viewModelFactory)[StatisticsOverviewVM::class.java]

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false)

        viewModel.stationWithTime.observe(viewLifecycleOwner, Observer {
            createAdapter(it)
        })

        binding.statisticsRecycler.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    private fun createAdapter(data: List<Groupable>) {
        val adapter = StatisticsAdapter(::onIntent, data)
        binding.statisticsRecycler.adapter = adapter
    }

    private fun onIntent(intent: StatisticsIntent) {
        when (intent) {
            is SelectExerciseIntent -> openExerciseDialog(intent)
            else -> {
                // DO nothing
            }
        }

    }

    private fun openExerciseDialog(intent: SelectExerciseIntent) {
        // TODO open dialog
        Toast.makeText(
            this.context,
            "Opened exercise ${intent.stationId} for date ${(intent.date.convertToLocalDateString()).resolve(
                this.context!!
            )}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
