package cl.smq.indicatorapp.ui.indicators

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.smq.indicatorapp.R
import cl.smq.indicatorapp.data.entities.IndicatorDetail
import cl.smq.indicatorapp.databinding.IndicatorListFragmentBinding
import cl.smq.indicatorapp.ui.adapter.ItemAdapter
import cl.smq.indicatorapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IndicatorListFragment : Fragment(), ItemAdapter.IndicatorItemListener {

    private lateinit var binding: IndicatorListFragmentBinding
    private val viewModel: IndicatorListViewModel by viewModels()
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = IndicatorListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        binding.listFragmentSortButton.setOnClickListener { sortIndicator() }
        binding.listFragmentSearch.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = ItemAdapter(this)
        binding.listFragmentRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.listFragmentRecycler.adapter = adapter
        binding.listFragmentRecycler.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

    }

    private fun setupObservers() {
        viewModel.indicators.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.onSuccess ->{
                    if (it.data != null) {
                        adapter.setItems(it.data.getIndicatorArray() as ArrayList<IndicatorDetail>)
                        binding.listFragmentProgress.visibility = View.GONE
                    }
                }
                Resource.Status.onError ->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.onLoading ->{
                    binding.listFragmentProgress.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClickedIndicator(indicatorCode: String) {
        findNavController().navigate(
            R.id.action_indicatorListFragment_to_indicatorDetailFragment,
            bundleOf("code" to indicatorCode)
        )
    }


    fun sortIndicator(){
        adapter.sortIndicator()
    }
}