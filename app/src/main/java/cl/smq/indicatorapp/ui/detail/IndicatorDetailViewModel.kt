package cl.smq.indicatorapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import cl.smq.indicatorapp.data.entities.IndicatorDetail
import cl.smq.indicatorapp.data.repository.IndicatorRepository
import cl.smq.indicatorapp.utils.Resource

class IndicatorDetailViewModel  @ViewModelInject constructor(
    private val repository: IndicatorRepository
) : ViewModel() {

    private val _code = MutableLiveData<String>()
    private val _indicatorDetail = _code.switchMap { code ->
        repository.getIndicatorDetail(code)
    }

    val indicatorDetail: LiveData<Resource<IndicatorDetail>> = _indicatorDetail

    fun star(code: String){
        _code.value = code
    }
}