package com.dr.trainup.trainingeditor.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dr.trainup.trainingeditor.R
import com.dr.trainup.trainingeditor.databinding.TrainingEditorFragmentBinding
import com.dr.trainup.trainingeditor.ui.vm.TrainingEditorViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class TrainingEditorFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingEditorFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TrainingEditorViewModel

    private lateinit var binding: TrainingEditorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.training_editor_fragment, container, false)
        binding = TrainingEditorFragmentBinding.bind(view)

        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[TrainingEditorViewModel::class.java]
        binding.vm = viewModel
        viewModel.addButtonLiveData.observe(viewLifecycleOwner, Observer { /*addExerciseParameter()*/ })

        //   setUpTrainingParameters()
    }
}

/*  // in the future, initially load from db, in the future ^ 2, use button add parameter
  private fun setUpTrainingParameters() {
      addExerciseParameter("1", "Sitzeinstellung", 4, "")
      addExerciseParameter("2", "Gewicht", 80, "kg")
      addExerciseParameter("3", "Wiederholungen", 11, "mal")
  }

  private fun persist() {

  }

  private fun addExerciseParameter(tag: String, text: String, value: Int, unit: String) {
      // TODO outsource mapping
      val parameterItem = ParameterItem(tag, text, value, unit)
      viewModel.parameterItems[tag] = parameterItem
      val view = LayoutInflater.from(requireContext()).inflate(R.layout.parameter_view, parameter_container, false)
      val binding = ParameterViewBinding.bind(view)
      // TODO: Viewmodel für jeden parameter oder gemeinsames, darin die daten speichern. Daten auslesen dann aus diesem VM,
      // so dass man die generierten views gar nicht mehr anfassen muss
      binding.itemData = parameterItem
      view.tag = tag
      parameter_container.addView(view)
  }
}*/
