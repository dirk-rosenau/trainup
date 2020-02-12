package com.dr.trainup.ui.fragments


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.trainup.R
import com.dr.trainup.databinding.FragmentOverviewBinding
import com.dr.trainup.ui.adapter.ExerciseOverviewAdapter
import com.dr.trainup.ui.vm.ExerciseOverviewItemVM
import com.dr.trainup.ui.vm.OverviewFragmentVM
import com.trainup.common.util.invokeIfNotConsumed
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_overview.*
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

        viewModel.itemSelected.observe(viewLifecycleOwner) {
            it.invokeIfNotConsumed { navigateToTrainingView(it) }
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

    private fun startActionMode() {
        actionMode = (activity as? AppCompatActivity)?.run {
            startSupportActionMode(
                createActionModeCallback()
            )
        }
    }

    private fun createActionModeCallback(): ActionMode.Callback {
        return object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.menu_edit -> handleMenuEditClick()
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

    private fun handleMenuEditClick() {
        val navController = getNavController()
        val items = viewModel.getSelectedItems()
        items.firstOrNull()?.let {
            val bundle = Bundle()
            bundle.putLong("id", it)
            navController.navigate(R.id.trainingEditActivity, bundle)
        }
        exercises.postDelayed({ exitActionMode() }, 200)
    }


    private fun navigateToTrainingView(id: Long) {
        val navController = getNavController()
        val bundle = Bundle()
        bundle.putLong("id", id)
        // TODO maybe safe args?
        navController.navigate(R.id.action_overviewFragment_to_exerciseFragment, bundle)
    }

    private fun getNavController() =
        findNavController(activity as FragmentActivity, R.id.nav_fragment)

    private fun exitActionMode() {
        actionMode?.finish()
        actionMode = null
        viewModel.deselectItems()
    }
}
