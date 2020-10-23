package cl.smq.indicatorapp.ui.indicators

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.smq.indicatorapp.R

class IndicatorListFragment : Fragment() {

    companion object {
        fun newInstance() = IndicatorListFragment()
    }

    private lateinit var viewModel: IndicatorListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.indicator_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IndicatorListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}