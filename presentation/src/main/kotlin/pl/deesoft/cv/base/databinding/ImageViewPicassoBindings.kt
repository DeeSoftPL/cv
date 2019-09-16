package pl.deesoft.cv.base.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ImageViewPicassoBindings @Inject constructor(
    private val picasso: Picasso
) {

    @BindingAdapter("imageUrl")
    fun bindImageUrl(view: ImageView, imageUrl: String?) {
        imageUrl?.let {
            picasso.load(imageUrl).into(view, object : Callback {

                override fun onSuccess() {}

                override fun onError() {
                    view.setImageDrawable(null)
                }
            })
        } ?: run {
            view.setImageDrawable(null)
        }
    }
}
