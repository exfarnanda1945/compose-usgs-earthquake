package com.example.composeusgsearthquake.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.composeusgsearthquake.data.api.QuakeApi
import com.example.composeusgsearthquake.data.paging.QuakePagingSource
import com.example.composeusgsearthquake.domain.model.Quake
import com.example.composeusgsearthquake.domain.repository.IQuakeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuakeRepository @Inject constructor(private val api: QuakeApi) : IQuakeRepository {
    override fun getList(): Flow<PagingData<Quake>> = Pager(
        config = PagingConfig(
            enablePlaceholders = false,
            pageSize = 10,
            initialLoadSize = 20
        ),
        pagingSourceFactory = { QuakePagingSource(api) }
    ).flow
}