package com.example.ezetap.ui.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ezetap.data.repository.Repository

class FormViewModelFactory(private val mRepository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            return FormViewModel(mRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}