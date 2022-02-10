package com.example.ezetap.ui.formdata

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

class FormDataViewModel() : BaseViewModel()