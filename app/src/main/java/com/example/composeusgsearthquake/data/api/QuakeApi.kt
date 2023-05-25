package com.example.composeusgsearthquake.data.api

import com.example.composeusgsearthquake.constant.RemoteSourceConstant
import com.example.composeusgsearthquake.data.dto.QuakeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuakeApi {
    @GET("query")
    fun getList(
        @Query(RemoteSourceConstant.QUERY_END_TIME) endTime: String,
        @Query(RemoteSourceConstant.QUERY_LIMIT) pageSize: Int,
        @Query(RemoteSourceConstant.QUERY_OFFSET) page: Int,
        @Query(RemoteSourceConstant.QUERY_FORMAT) format: String = "geojson",
        @Query(RemoteSourceConstant.QUERY_ORDER_BY) orderBy: String = "time"
    ): QuakeDto
}