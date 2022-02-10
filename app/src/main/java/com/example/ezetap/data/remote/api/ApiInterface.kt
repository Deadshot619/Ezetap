package com.example.ezetap.data.remote.api

import com.example.ezetap.model.network.CustomUiNetworkModel
import com.example.ezetap.utils.API_URL
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(API_URL)
    fun getUiDataApiAsync(): Deferred<CustomUiNetworkModel>
}