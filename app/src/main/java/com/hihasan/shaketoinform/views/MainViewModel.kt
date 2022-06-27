package com.hihasan.shaketoinform.views

import androidx.lifecycle.ViewModel
import com.hihasan.shaketoinform.data.entity.BugEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {

    fun insertBug(bugEntity: BugEntity) = CoroutineScope(IO).launch { mainRepository.insertBugData(bugEntity) }
}