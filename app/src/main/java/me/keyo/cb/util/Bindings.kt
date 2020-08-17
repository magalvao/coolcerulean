package me.keyo.cb.util

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.BindingAdapter

class Bindings {
    companion object {
        @JvmStatic @BindingAdapter("onEditorEnterAction")
        fun EditText.onEditorEnterAction(f: Function1<String, Unit>?) {

            if (f == null) setOnEditorActionListener(null)
            else setOnEditorActionListener { v, actionId, event ->

                val imeAction = when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> true
                    else -> false
                }

                val keydownEvent = event?.keyCode == KeyEvent.KEYCODE_ENTER
                        && event.action == KeyEvent.ACTION_DOWN

                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)

                if (imeAction or keydownEvent)
                    true.also {
                        f(v.editableText.toString())
                    }
                else false
            }
        }
    }

}