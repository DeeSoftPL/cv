package pl.deesoft.cv.base.databinding.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import pl.deesoft.cv.BR
import java.util.*
import javax.inject.Inject


class BindingRecyclerAdapter @Inject constructor(

) : RecyclerView.Adapter<BindingViewHolder<*>>() {

    private val delegatesMap: HashMap<Class<out Any>, Delegate<Any, ViewDataBinding>?> = hashMapOf()

    var items: List<Any> = emptyList()
        private set

    fun setItems(items: List<Any>, notifyDataSetChanged: Boolean) {
        this.items = items
        if (notifyDataSetChanged) {
            notifyDataSetChanged()
        }
    }

    fun registerViewModels(vararg pair: Pair<Class<out Any>, Int>) {
        pair.forEach { registerViewModel(it.first, it.second) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<*> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
        )
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<*>, position: Int) {
        val item = getItem(position)
        try {
            getDelegate(position)?.bindAction?.let { it(holder.binding, item) }
            holder.binding.executePendingBindings()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return getDelegate(position)!!.getLayoutRes(getItem(position))
    }

    private fun registerViewModel(viewModelClass: Class<out Any>, layoutRes: Int) {
        registerDelegate(
                viewModelClass,
                layoutRes
        ) { viewDataBinding, viewModel ->
            viewDataBinding.setVariable(BR.viewModel, viewModel)
        }
    }

    private fun registerDelegate(
            typeClass: Class<out Any>,
            layoutRes: Int,
            bindAction: (viewDataBinding: ViewDataBinding, viewModel: Any) -> Unit
    ) {
        delegatesMap[typeClass] =
                Delegate(layoutRes, bindAction)
    }

    private fun getDelegate(position: Int): Delegate<Any, ViewDataBinding>? {
        val itemType = getItem(position).javaClass
        var delegate: Delegate<Any, ViewDataBinding>? = delegatesMap[itemType]
        if (delegate == null) {
            for (delegateType in delegatesMap.keys) {
                if (delegateType.isAssignableFrom(itemType)) {
                    delegate = delegatesMap[delegateType]
                    delegatesMap[itemType] = delegate
                    return delegate
                }
            }
            throw RuntimeException(
                    "No delegate registered for class: %s".format(
                            itemType
                    )
            )
        }
        return delegate
    }

    private fun getItem(position: Int): Any {
        return items[position]
    }

    private open class Delegate<in Type, in Binding : ViewDataBinding> internal constructor(
            val layoutRes: Int,
            val bindAction: (viewDataBinding: Binding, viewModel: Type) -> Unit
    ) {

        open fun getLayoutRes(type: Type): Int {
            return layoutRes
        }
    }
}