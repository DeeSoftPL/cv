package pl.deesoft.cv.base.mvvm

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Lazy
import pl.deesoft.cv.base.BaseActivity
import javax.inject.Inject

abstract class ViewModelActivity<VM : BaseViewModel, B : ViewDataBinding> : BaseActivity() {

    @Inject
    internal lateinit var lazyViewModel: Lazy<VM>

    protected lateinit var viewModel: VM

    private lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = retrieveViewModel()
        lifecycle.addObserver(viewModel)
        binding = onCreateViewDataBinding()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }

    protected abstract fun onCreateViewDataBinding(): B

    override fun onDestroy() {
        super.onDestroy()
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