package com.dr.trainup.trainingview.ui


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
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TrainingViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrainingViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragementTrainingViewBinding

    private lateinit var viewModel: TrainingViewVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

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

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrainingViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
