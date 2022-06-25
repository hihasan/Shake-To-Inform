package com.hihasan.shaketoinform.data.dao

import androidx.room.Dao
import com.hihasan.shaketoinform.data.entity.SuggestionEntity
import com.hihasan.shaketoinform.utils.BaseDao

@Dao
interface SuggestionsDao : BaseDao<SuggestionEntity> {
}