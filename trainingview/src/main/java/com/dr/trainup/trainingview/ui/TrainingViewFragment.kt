package com.dr.trainup.trainingview.ui


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dr.trainup.trainingview.R
import com.dr.trainup.trainingview.databinding.FragementTrainingViewBinding
import com.dr.trainup.trainingview.vm.TrainingViewVM
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_STATION_ID = "station_id"

/**
 * A simple [Fragment] subclass.
 * Use the [TrainingViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrainingViewFragment : Fragment() {
    private var stationId: Long? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragementTrainingViewBinding

    private lateinit var viewModel: TrainingViewVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stationId = it.getLong(ARG_STATION_ID)
        }

    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragement_training_view, container, false)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[TrainingViewVM::class.java]

        binding = FragementTrainingViewBinding.bind(view)
        binding.vm = viewModel
        // TODO start-station auswählen
        viewModel.init(1)



        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrainingViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_STATION_ID, param1)
                }
            }
    }
}
