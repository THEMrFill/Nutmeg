package com.nutmeg.philip.arnold.mainpage

import com.nutmeg.philip.arnold.retrofit.data.PostData

interface DataResponseInterface {
    fun ReturnData(data: ArrayList<PostData>)
    fun ReturnError(error: Throwable)
}