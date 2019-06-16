package com.dr.trainup.trainingeditor.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dr.trainup.trainingeditor.R
import com.dr.trainup.trainingeditor.TrainingEditorViewModel
import com.dr.trainup.trainingeditor.databinding.TrainingEditorFragmentBinding
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
        // TODO: Inject vm
        // viewModel = ViewModelProviders.of(this).get(TrainingEditorViewModel::class.java)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[TrainingEditorViewModel::class.java]
        binding.vm = viewModel
    }

}
