package de.trainup.common.extensions


import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.SparseArray
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private inline val Fragment.args: Bundle
    get() = (arguments ?: Bundle()).also { arguments = it }

private inline fun <F : Fragment> F.withArgsBundle(bundleClosure: Bundle.() -> Unit): F =
    also { args.bundleClosure() }

fun <F : Fragment> F.withArgs(vararg data: Pair<String, Any?>): F = withArgsBundle {
    data.forEach { (key, value) ->
        when (value) {
            null -> putSerializable(key, null)
            is Boolean -> putBoolean(key, value)
            is BooleanArray -> putBooleanArray(key, value)
            is Bundle -> putBundle(key, value)
            is Byte -> putByte(key, value)
            is ByteArray -> putByteArray(key, value)
            is Char -> putChar(key, value)
            is CharArray -> putCharArray(key, value)
            is Double -> putDouble(key, value)
            is DoubleArray -> putDoubleArray(key, value)
            is Float -> putFloat(key, value)
            is FloatArray -> putFloatArray(key, value)
            is Int -> putInt(key, value)
            is IntArray -> putIntArray(key, value)
            is Long -> putLong(key, value)
            is LongArray -> putLongArray(key, value)
            is Short -> putShort(key, value)
            is ShortArray -> putShortArray(key, value)
            is IBinder -> putBinder(key, value)
            is String -> putString(key, value)
            is CharSequence -> putCharSequence(key, value)
            is Parcelable -> putParcelable(key, value)
            is Serializable -> putSerializable(key, value)
            else -> {
            } // do nothing
        }
    }
}

fun Fragment.initializeFragmentToolbar(
    toolbar: Toolbar?,
    @StringRes titleResId: Int = 0
) {
    (activity as? AppCompatActivity)?.also { activity ->
        activity.setSupportActionBar(toolbar)
        if (titleResId != 0) {
            activity.supportActionBar?.setDisplayShowTitleEnabled(true)
            activity.supportActionBar?.setTitle(titleResId)
        } else {
            activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }
}

private inline fun <T> Bundle.putAll(
    vararg data: Pair<String, T>,
    insert: Bundle.(String, T) -> Unit
) {
    data.forEach { (key, value) ->
        insert(key, value)
    }
}

inline fun <reified VIEW_MODEL : ViewModel> Fragment.bindViewModel(): Lazy<VIEW_MODEL> =
    lazy { createViewModel<VIEW_MODEL>() }

inline fun <reified VIEW_MODEL : ViewModel> Fragment.createViewModel() =
    ViewModelProviders.of(this)[VIEW_MODEL::class.java]

inline fun <reified BINDING : ViewDataBinding> Fragment.bindDataBinding(): ReadOnlyProperty<Fragment, BINDING> =
    object : ReadOnlyProperty<Fragment, BINDING> {
        override fun getValue(thisRef: Fragment, property: KProperty<*>): BINDING =
            checkNotNull(view) { "View must be not null!" }
                .let { DataBindingUtil.bind<BINDING>(it) }
                .let { checkNotNull(it) { "Binding must be not null!" } }
    }

fun Fragment.closeKeyboard() {
    view?.let { view ->
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }
}
