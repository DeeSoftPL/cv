package pl.deesoft.cv.base.databinding.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class BindingRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private val adapter: BindingRecyclerAdapter =
            BindingRecyclerAdapter()

    private var delegatesRegistered = false

    private var pendingItems: List<Any>? = null

    init {
        setAdapter(adapter)
    }

    fun registerDelegates(callback: (bindingRecyclerAdapter: BindingRecyclerAdapter) -> Unit) {
        callback(adapter)
        delegatesRegistered = true
        pendingItems?.let {
            adapter.setItems(it, true)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setAdapter(adapter)
    }

    override fun getAdapter(): BindingRecyclerAdapter? {
        return adapter
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        getAdapter()?.let {
            setAdapter(null)
        }
    }
}
