package com.example.shemajamebeli_10.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shemajamebeli_10.data.remote.model.DTOItem
import com.example.shemajamebeli_10.domain.repository.MyRepository
import com.example.shemajamebeli_10.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MyRepository): ViewModel() {
    private val _myState =
        MutableStateFlow<ResponseState<List<DTOItem>>>(ResponseState.Empty()) //mutable state flow
    val myState: StateFlow<ResponseState<List<DTOItem>>> = _myState //immutable state flow

    fun getInfo() {
        viewModelScope.launch {
            _myState.emit(ResponseState.Loading())
            val data = repository.doNetworkCall()
            data.collect{
                _myState.value = it
            }
        }

    }
}


