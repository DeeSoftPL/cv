package pl.deesoft.cv.base.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ViewBindings @Inject constructor() {

    @BindingAdapter("visibility")
    fun setVisibility(
        view: View,
        visibility: Boolean?
    ) {
        visibility?.let {
            view.visibility = if (visibility) View.VISIBLE else View.GONE
        }
    }
}
