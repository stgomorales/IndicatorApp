package cl.smq.indicatorapp.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.smq.indicatorapp.data.entities.IndicatorDetail
import cl.smq.indicatorapp.data.entities.Serie
import cl.smq.indicatorapp.databinding.IndicatorDetailFragmentBinding
import cl.smq.indicatorapp.ui.adapter.SerieAdapter
import cl.smq.indicatorapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class IndicatorDetailFragment : Fragment(){

    private lateinit var binding: IndicatorDetailFragmentBinding
    private val viewModel: IndicatorDetailViewModel by viewModels()
    private lateinit var adapter: SerieAdapter
    private var indicatorDetail: IndicatorDetail? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = IndicatorDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("code")?.let { viewModel.star(it) }
        setupRecyclerView()
        setupObservers()
        binding.indicatorDetailShare.setOnClickListener { shareIndicator() }
    }

    private fun setupObservers(){
        viewModel.indicatorDetail.observe(viewLifecycleOwner, Observer{
            when(it.status){
                Resource.Status.onSuccess ->{
                    if (it.data != null) {
                        indicatorDetail = it.data
                        bindIndicatorDetail(it.data)
                        binding.indicatorDetailProgress.visibility = View.GONE
                    }
                }
                Resource.Status.onError ->{
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                }
                Resource.Status.onLoading ->{
                    binding.indicatorDetailProgress.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = SerieAdapter()
        binding.indicatorDetailSerie.layoutManager = LinearLayoutManager(requireContext())
        binding.indicatorDetailSerie.adapter = adapter
        binding.indicatorDetailSerie.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
    }

    private fun bindIndicatorDetail(indicatorDetail: IndicatorDetail) {
        binding.indicatorDetailCode.text = indicatorDetail.code
        binding.indicatorDetailName.text = indicatorDetail.name
        adapter.setSeries(indicatorDetail.series as ArrayList<Serie>)
    }

    private fun shareIndicator(){
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getShareText())
            putExtra(Intent.EXTRA_SUBJECT, "${indicatorDetail?.name} al ${simpleDateFormat.format(adapter.getIndicatorCurrentValue().date)}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun getShareText():String{
        var text: String = "Valor de ${indicatorDetail?.name} es de ${adapter.getIndicatorCurrentValue().value}"
        if (indicatorDetail?.unitMeasure?.toUpperCase().equals("Porcentaje".toUpperCase()))
            text = text.plus(" % (porciento")
        else
            text = text.plus(" ${indicatorDetail?.unitMeasure}")
        return text
    }
}