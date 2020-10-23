package cl.smq.indicatorapp.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.smq.indicatorapp.R

class IndicatorDetailFragment : Fragment() {

    companion object {
        fun newInstance() = IndicatorDetailFragment()
    }

    private lateinit var viewModel: IndicatorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.indicator_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IndicatorDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}