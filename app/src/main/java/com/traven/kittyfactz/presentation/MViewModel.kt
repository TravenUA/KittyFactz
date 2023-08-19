package com.traven.kittyfactz.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.traven.kittyfactz.Const
import com.traven.kittyfactz.model.Repo
import kotlinx.coroutines.launch

class MViewModel : ViewModel() {

    private val _inData = MutableLiveData<String>()
    val outData: LiveData<String> = _inData

    private val repo: Repo = Repo()

    suspend fun getFact() {
        try {
            val fact = repo.getFact()
            _inData.postValue(fact.fact)
        } catch (_: Exception) {
            _inData.postValue(
                Const.STATE_MSG_ERR
            )
        }
    }
}