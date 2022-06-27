package com.hihasan.shaketoinform.views

import com.hihasan.shaketoinform.data.entity.BugEntity
import com.hihasan.shaketoinform.utils.App
import com.hihasan.shaketoinform.utils.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel (private val mainRepository: MainRepository) : BaseViewModel(context = App.getAppContext()) {

    fun insertBug(bugEntity: BugEntity) = CoroutineScope(IO).launch { mainRepository.insertBugData(bugEntity) }
}