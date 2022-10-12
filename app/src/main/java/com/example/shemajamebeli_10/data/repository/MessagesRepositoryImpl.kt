package com.example.shemajamebeli_10.data.repository

import android.os.Message
import com.example.shemajamebeli_10.data.remote.MessagesApi
import com.example.shemajamebeli_10.data.remote.model.DTO
import com.example.shemajamebeli_10.data.remote.model.DTOItem
import com.example.shemajamebeli_10.domain.repository.MyRepository
import com.example.shemajamebeli_10.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val api: MessagesApi
) : MyRepository {
    override suspend fun doNetworkCall(): Flow<ResponseState<List<DTOItem>>> = flow {
        try {
            val response: Response<List<DTOItem>> = api.doNetworkCall()
            val body: List<DTOItem>? = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Error(response.errorBody().toString()))
            }
        } catch (e: Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }

}