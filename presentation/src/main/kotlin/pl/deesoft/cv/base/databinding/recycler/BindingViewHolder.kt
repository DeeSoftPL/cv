package pl.deesoft.cv.base.databinding.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<out Binding : ViewDataBinding> constructor(
        val binding: Binding
) : RecyclerView.ViewHolder(binding.root) {
    var viewModel: Any? = null
}
