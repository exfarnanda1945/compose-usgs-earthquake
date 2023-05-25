package com.example.composeusgsearthquake.data.paging

import android.icu.util.Calendar
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composeusgsearthquake.data.api.QuakeApi
import com.example.composeusgsearthquake.data.mapper.toQuake
import com.example.composeusgsearthquake.domain.model.Quake
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class QuakePagingSource @Inject constructor(private val api: QuakeApi) :
    PagingSource<Int, Quake>() {
    override fun getRefreshKey(state: PagingState<Int, Quake>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quake> {
        val page = params.key ?: PAGE_INITIALIZE

        return try {
            val response = api.getList(
                endTime = SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                ).format(Date(Calendar.getInstance().time.time)),
                pageSize = params.loadSize,
                page = page
            )
            LoadResult.Page(
                data = response.toQuake(),
                nextKey = response.metadata?.offset!! + 1,
                prevKey = if (page == PAGE_INITIALIZE) null else response.metadata.offset - 1
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_INITIALIZE = 1
    }
}