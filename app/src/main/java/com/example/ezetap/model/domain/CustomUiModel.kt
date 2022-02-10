package com.example.ezetap.model.domain

import android.os.Parcelable
import com.example.ezetap.utils.enum.ViewType
import kotlinx.parcelize.Parcelize

data class CustomUiModel(
    val headingText: String,
    val logoUrl: String,
    val uiData: List<UiData>
)

@Parcelize
data class UiData(
    val hint: String,
    val key: String,
    val uiType: ViewType,
    val value: String,
    var userInput: String = ""
): Parcelable