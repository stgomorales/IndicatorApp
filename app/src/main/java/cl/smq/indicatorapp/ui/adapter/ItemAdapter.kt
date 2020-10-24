package cl.smq.indicatorapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.smq.indicatorapp.R
import cl.smq.indicatorapp.data.entities.IndicatorDetail
import cl.smq.indicatorapp.data.entities.Serie
import cl.smq.indicatorapp.databinding.IndicatorRowBinding
import java.text.SimpleDateFormat

class ItemAdapter(private val listener: IndicatorItemListener, private val isSerie: Boolean = false): RecyclerView.Adapter<IndicatorViewHolder>() {

    interface IndicatorItemListener {
        fun onClickedIndicator(indicatorCode: String)
    }

    private val items = ArrayList<IndicatorDetail>()
    private val series = ArrayList<Serie>()

    fun setItems(items: ArrayList<IndicatorDetail>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setSeries(series: ArrayList<Serie>){
        this.series.clear()
        series.sortedByDescending{ it.date}
        this.series.addAll(series)
        notifyDataSetChanged()
    }

    fun getIndicatorCurrentValue(): String{
        return series[0].toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val binding: IndicatorRowBinding = IndicatorRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndicatorViewHolder(
            binding,
            listener
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) = holder.bind(items.get(position), isSerie)


}

class IndicatorViewHolder(private val indicatorRowBinding: IndicatorRowBinding, private val listener: ItemAdapter.IndicatorItemListener) : RecyclerView.ViewHolder(indicatorRowBinding.root),
    View.OnClickListener {

    private lateinit var indicator: IndicatorDetail

    init {
        indicatorRowBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: IndicatorDetail, isSerie: Boolean) {
        this.indicator = item
        if (isSerie){
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            indicatorRowBinding.itemIconLayout.visibility = View.GONE
            indicatorRowBinding.itemUnit.visibility = View.GONE
            indicatorRowBinding.itemName.text = simpleDateFormat.format(item.date)
            indicatorRowBinding.itemValue.text = item.value.toString()
        }
        else{
            indicatorRowBinding.itemName.text = item.name
            indicatorRowBinding.itemValue.text = item.value.toString()
            indicatorRowBinding.itemUnit.text = item.unitMeasure
            if (item.unitMeasure.toUpperCase().equals("Porcentaje".toUpperCase()))
                indicatorRowBinding.itemIcon.setImageResource(R.drawable.ic_percent)
        }

    }

    override fun onClick(v: View?) {
        listener.onClickedIndicator(indicator.code)
    }
}