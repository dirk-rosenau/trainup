package de.trainup.common.ui


import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.MenuRes

class PrimaryActionModeCallback(
    @MenuRes private val menuResId: Int,
    private val title: String,
    private val subtitle: String,
    private val onActionItemClickListener: (Int) -> Unit
) : ActionMode.Callback {

    private var mode: ActionMode? = null


    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        this.mode = mode
        mode.menuInflater.inflate(menuResId, menu)
        mode.title = title
        mode.subtitle = subtitle
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode) {
        this.mode = null
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        onActionItemClickListener.invoke(item.itemId)
        mode.finish()
        return true
    }

    fun finishActionMode() {
        mode?.finish()
    }
}