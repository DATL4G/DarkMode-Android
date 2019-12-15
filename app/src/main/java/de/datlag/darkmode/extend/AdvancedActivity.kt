package de.datlag.darkmode.extend

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import de.datlag.darkmode.R
import io.github.inflationx.viewpump.ViewPumpContextWrapper


abstract class AdvancedActivity : AppCompatActivity() {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    fun <ViewT: View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
        return lazy(LazyThreadSafetyMode.NONE) {
            findViewById<ViewT>(idRes)
        }
    }

    fun applyDialogAnimation(alertDialog: AlertDialog): AlertDialog {
        alertDialog.window!!.attributes.windowAnimations = R.style.MaterialDialogAnimation
        return alertDialog
    }

    fun browserIntent(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(browserIntent)
    }
}