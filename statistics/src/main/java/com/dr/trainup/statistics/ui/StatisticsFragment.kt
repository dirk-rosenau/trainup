package com.dr.trainup.statistics.ui


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dr.trainup.statistics.R
import com.dr.trainup.statistics.vm.StatisticsOverviewVM
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class StatisticsFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: StatisticsOverviewVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this, viewModelFactory)[StatisticsOverviewVM::class.java]

        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}
