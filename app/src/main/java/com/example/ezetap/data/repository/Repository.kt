package com.example.ezetap.data.repository

import android.util.Log
import com.example.ezetap.data.remote.APIClient
import com.example.ezetap.data.remote.api.ApiInterface
import com.example.ezetap.model.State
import com.example.ezetap.model.domain.CustomUiModel
import com.example.ezetap.model.mapper.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository() {
    private val mServices: ApiInterface by lazy {
        APIClient.getClient().create(ApiInterface::class.java)
    }

    fun getUiData() =
        flow<State<CustomUiModel>> {
            emit(State.loading())

            val result = mServices.getUiDataApiAsync().await()

            emit(State.success(result.toDomainModel()))
        }.catch {
            // If exception is thrown, emit failed state along with message.
            Log.e("EzetapError", it.message.toString())
            emit(State.failed(it.message.toString()))
        }.flowOn(Dispatchers.IO)
}