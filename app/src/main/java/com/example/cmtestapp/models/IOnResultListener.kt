package com.example.cmtestapp.models

import com.example.cmtestapp.models.dto.ICharacterResponse

interface OnResultListener {
    fun setResult(result: ICharacterResponse)
}
