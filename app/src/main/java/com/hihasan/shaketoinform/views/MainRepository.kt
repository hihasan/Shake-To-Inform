package com.hihasan.shaketoinform.views

import com.hihasan.shaketoinform.data.dao.BugDao
import com.hihasan.shaketoinform.data.entity.BugEntity

class MainRepository(private val bugDao : BugDao) {

    suspend fun insertBugData(bugEntity: BugEntity){
        bugDao.insert(bugEntity)
    }
}