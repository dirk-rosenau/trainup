package com.dr.trainup.trainingeditor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders


class TrainingEditorFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingEditorFragment()
    }

    private lateinit var viewModel: TrainingEditorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.training_editor_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrainingEditorViewModel::class.java)
        // TODO: Use the ViewModel

    }

}
