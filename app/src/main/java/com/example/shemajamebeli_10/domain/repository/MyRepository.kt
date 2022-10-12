package com.example.shemajamebeli_10.domain.repository

import com.example.shemajamebeli_10.data.remote.model.DTOItem
import com.example.shemajamebeli_10.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface MyRepository {
    suspend fun doNetworkCall(): Flow<ResponseState<List<DTOItem>>>
}