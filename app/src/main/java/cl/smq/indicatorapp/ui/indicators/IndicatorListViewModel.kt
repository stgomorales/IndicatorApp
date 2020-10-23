package cl.smq.indicatorapp.ui.indicators

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import cl.smq.indicatorapp.data.repository.IndicatorRepository

class IndicatorListViewModel @ViewModelInject constructor(
    private val repository: IndicatorRepository): ViewModel(){
    val indicators = repository.getAllIndicators()
}