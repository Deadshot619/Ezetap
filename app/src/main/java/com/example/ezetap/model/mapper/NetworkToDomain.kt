package com.example.ezetap.model.mapper

import com.example.ezetap.model.domain.CustomUiModel
import com.example.ezetap.model.domain.UiData
import com.example.ezetap.model.network.CustomUiNetworkModel
import com.example.ezetap.utils.enum.getViewType

fun CustomUiNetworkModel.toDomainModel() = CustomUiModel(
    headingText = headingText,
    logoUrl = logoUrl,
    uiData = uiNetworkData.map {
        UiData(
            hint = it.hint ?: "",
            key = it.key ?: "",
            uiType = getViewType(it.uiType),
            value = it.value ?: "",
        )
    }
)