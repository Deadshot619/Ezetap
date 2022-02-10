package com.example.ezetap.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CustomUiNetworkModel(
    @SerializedName("heading-text") @Expose val headingText: String,
    @SerializedName("logo-url") @Expose val logoUrl: String,
    @SerializedName("uidata") @Expose val uiNetworkData: List<UiNetworkData>
)

data class UiNetworkData(
    @SerializedName("hint") @Expose val hint: String?,
    @SerializedName("key") @Expose val key: String?,
    @SerializedName("uitype") @Expose val uiType: String,
    @SerializedName("value") @Expose val value: String?
)