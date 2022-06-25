package com.hihasan.shaketoinform.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hihasan.shaketoinform.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TBL_SUGGESTIONS)
data class SuggestionEntity (
    @PrimaryKey(autoGenerate = true)
    val sl_no : Int
    )