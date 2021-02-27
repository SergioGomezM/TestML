package com.colapp.testml.repository

class ResRepository<T>(var origin: String) {
    var data: T? = null
    var message: String = RepoConst.RESULT_MESSAGE_OK
    var errorCode: Int = RepoConst.ERROR_CODE_OK
}

