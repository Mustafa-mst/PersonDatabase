package com.sagul.kotlinroompr.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class baseViewModel(app:Application):AndroidViewModel(app),CoroutineScope {
    var job= Job()
    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main
}