package pl.deesoft.cv.base.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Lazy
import pl.deesoft.cv.BR
import pl.deesoft.cv.base.BaseFragment
import javax.inject.Inject

abstract class ViewModelFragment<VM : BaseViewModel, B : ViewDataBinding> : BaseFragment() {

    @Inject
    internal lateinit var lazyViewModel: Lazy<VM>

    protected lateinit var viewModel: VM

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = retrieveViewModel()
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateViewDataBinding(inflater, container).apply { binding = this }.root
    }

    abstract fun onCreateViewDataBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    private fun retrieveViewModel(): VM {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return lazyViewModel.get() as T
            }
        }).get(lazyViewModel.get()::class.java)
    }
}
