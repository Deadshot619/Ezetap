package com.example.ezetap.utils.enum

enum class ViewType {
    LABEL, EDITTEXT, BUTTON
}

fun getViewType(value: String): ViewType = when(value){
    "label" -> ViewType.LABEL
    "edittext" -> ViewType.EDITTEXT
    "button" -> ViewType.BUTTON
    else -> ViewType.LABEL
}