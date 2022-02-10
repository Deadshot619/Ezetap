package com.example.ezetap.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ezetap.data.repository.Repository
import com.example.ezetap.model.Event
import com.example.ezetap.model.State
import com.example.ezetap.model.domain.CustomUiModel
import com.example.ezetap.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FormViewModel(val mRepository: Repository) : BaseViewModel() {

    private var uiJob: Job? = null

    init {
        getData()
    }

    private val _uiData = MutableLiveData<Event<State<CustomUiModel>>>()
    val uiData: LiveData<Event<State<CustomUiModel>>>
        get() = _uiData

    fun getData(){
        uiJob?.cancel()
        uiJob = viewModelScope.launch {
            mRepository.getUiData().collect {
                _uiData.value = Event(it)
            }
        }
    }
}