package pl.deesoft.cv.base.mvvm

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import pl.deesoft.cv.App

abstract class BaseViewModel constructor(
    app: App
) : AndroidViewModel(app), LifecycleObserver