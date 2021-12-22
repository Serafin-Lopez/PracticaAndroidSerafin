package mx.com.qualitycode.practicaandroidserafin.helpers


import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Extension functions to help to reduce the boilerplate when setting up visibility to views.
 * Feel free to add extensions functions as you need.
 */

/**
 * Set visibility of a view to visible.
 * Usage: myView.show()
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Set visibility of a view to gone.
 * Usage: myView.hide()
 */
fun View.hide() {
    this.visibility = View.GONE
}

/**
 * Check if view is visible.
 * Usage: if (view.isVisible()) {...}
 * @return true if view is visible, false otherwise.
 */
fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

/**
 * Check if view is not visible.
 * Usage: if (view.isNotVisible()) {...}
 * @return true if view is not visible, false otherwise.
 */
fun View.isNotVisible(): Boolean {
    return this.visibility == View.GONE
}

/**
 * Use to show or hide view based on the parameter passed
 * Usage: view.shouldShow(true)
 */
fun View.shouldShow(shouldShow: Boolean) {
    if (shouldShow) this.show() else this.hide()
}

fun View.hideKeyBoard(){
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

