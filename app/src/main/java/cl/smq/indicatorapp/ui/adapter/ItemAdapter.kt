package cl.smq.indicatorapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import cl.smq.indicatorapp.R
import cl.smq.indicatorapp.data.entities.IndicatorDetail
import cl.smq.indicatorapp.databinding.IndicatorRowBinding
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(private val listener: IndicatorItemListener): RecyclerView.Adapter<IndicatorViewHolder>(),
    Filterable {

    interface IndicatorItemListener {
        fun onClickedIndicator(indicatorCode: String)
    }

    private var items = ArrayList<IndicatorDetail>()
    var oldItems = ArrayList<IndicatorDetail>()

    private var sortedAsc: Boolean = false

    fun setItems(items: ArrayList<IndicatorDetail>) {
        this.items.clear()
        this.items.addAll(items)
        this.oldItems.clear()
        this.oldItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val binding: IndicatorRowBinding = IndicatorRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndicatorViewHolder(
            binding,
            listener
        )
    }

    fun sortIndicator(){

        if (!sortedAsc) {
            items.sortWith(compareBy { it.name })
        } else{
            items.reverse()
        }
        notifyDataSetChanged()
        sortedAsc = !sortedAsc
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) = holder.bind(items.get(position))

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    items = oldItems
                } else {
                    val resultList = ArrayList<IndicatorDetail>()
                    for (item in oldItems) {
                        if (item.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)) || item.code.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(item)
                        }
                    }
                    items = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = items
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items = results?.values as ArrayList<IndicatorDetail>
                notifyDataSetChanged()
            }

        }
    }

}

class IndicatorViewHolder(private val indicatorRowBinding: IndicatorRowBinding, private val listener: ItemAdapter.IndicatorItemListener) : RecyclerView.ViewHolder(indicatorRowBinding.root),
    View.OnClickListener {

    private lateinit var indicator: IndicatorDetail

    init {
        indicatorRowBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: IndicatorDetail) {
        this.indicator = item
        indicatorRowBinding.itemName.text = item.name
        indicatorRowBinding.itemValue.text = item.value.toString()
        indicatorRowBinding.itemUnit.text = item.unitMeasure
        if (item.unitMeasure.toUpperCase().equals("Porcentaje".toUpperCase()))
            indicatorRowBinding.itemIcon.setImageResource(R.drawable.ic_percent)
        else
            indicatorRowBinding.itemIcon.setImageResource(R.drawable.ic_money)
    }

    override fun onClick(v: View?) {
        listener.onClickedIndicator(indicator.code)
    }
}