package com.example.ezetap.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

private var toast: Toast? = null

fun Fragment.showToast(text: String, durationLong: Boolean = false) {
    toast?.cancel()
    toast = Toast.makeText(this.requireContext(), text, if (durationLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
    toast?.show()
}