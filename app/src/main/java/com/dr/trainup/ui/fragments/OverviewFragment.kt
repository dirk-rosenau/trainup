package com.dr.trainup.ui.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.trainup.R
import com.dr.trainup.databinding.FragmentOverviewBinding
import com.dr.trainup.ui.adapter.ExerciseOverviewAdapter
import com.dr.trainup.ui.vm.ExerciseOverviewItemVM
import com.dr.trainup.ui.vm.OverviewFragmentVM
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentOverviewBinding
    private lateinit var viewModel: OverviewFragmentVM

    private var actionMode: ActionMode? = null

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

        viewModel.itemVms.observe(viewLifecycleOwner) {
            handleNewData(it)
        }

        viewModel.actionMode.observe(viewLifecycleOwner) {
            if (it) {
                startActionMode()
            } else {
                exitActionMode()
            }
        }

        viewModel.loadExercises()
    }

    private fun handleNewData(it: List<ExerciseOverviewItemVM>) {
        val adapter = ExerciseOverviewAdapter(it)
        binding.exercises.swapAdapter(adapter, true)
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


    private fun startActionMode() {
        actionMode = (activity as? AppCompatActivity)?.run {
            startSupportActionMode(
//                PrimaryActionModeCallback(
//                    R.menu.menu_overview_actionmode,
//                    "",
//                    "",
//                    ::handleActionModeItemClicked
//                )
                createActionModeCallback()
            )
        }
    }

    private fun createActionModeCallback(): ActionMode.Callback {
        return object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.menu_edit -> Log.d("actionmode", "edit")
                    R.id.menu_delete -> viewModel.deleteSelectedItems()
                }
                return true
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode?.menuInflater?.inflate(R.menu.menu_overview_actionmode, menu)
                mode?.title = "Blub"
                viewModel.actionModeEnabled = true
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                viewModel.actionModeEnabled = false
            }

        }
    }

    private fun exitActionMode() {
        actionMode?.finish()
        actionMode = null
        viewModel.deselectItems()
    }

    private fun handleActionModeItemClicked(id: Int) {
        when (id) {
            R.id.menu_edit -> Log.d("actionmode", "edit")
            R.id.menu_delete -> handleItemDelete()
        }
    }

    private fun handleItemDelete() {
        Log.d("actionmode", "delete")
        viewModel.deleteSelectedItems()
    }
}
