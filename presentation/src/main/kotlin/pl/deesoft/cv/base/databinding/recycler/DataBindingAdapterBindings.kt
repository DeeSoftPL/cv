package pl.deesoft.cv.base.databinding.recycler

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.Reusable
import javax.inject.Inject

@Reusable
class DataBindingAdapterBindings @Inject constructor() {

    @BindingAdapter("rowArray")
    fun bindRowArray(
            recyclerView: RecyclerView,
            rows: List<Any>
    ) {
        if (recyclerView.adapter !is BindingRecyclerAdapter) {
            recyclerView.adapter = BindingRecyclerAdapter()
        }
        val adapter = recyclerView.adapter as BindingRecyclerAdapter

        adapter.setItems(rows, true)
    }
}