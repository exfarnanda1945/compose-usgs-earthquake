package com.example.composeusgsearthquake.domain.repository

import androidx.paging.PagingData
import com.example.composeusgsearthquake.domain.model.Quake
import kotlinx.coroutines.flow.Flow

interface IQuakeRepository {
    fun getList(): Flow<PagingData<Quake>>
}