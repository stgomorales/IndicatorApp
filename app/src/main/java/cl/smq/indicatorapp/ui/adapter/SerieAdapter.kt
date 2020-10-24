package cl.smq.indicatorapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.smq.indicatorapp.data.entities.Serie
import cl.smq.indicatorapp.databinding.IndicatorRowBinding
import java.text.SimpleDateFormat

class SerieAdapter(): RecyclerView.Adapter<SerieViewHolder>() {

    private val series = ArrayList<Serie>()


    fun setSeries(series: ArrayList<Serie>){
        this.series.clear()
        series.sortedByDescending{ it.date}
        this.series.addAll(series)
        notifyDataSetChanged()
    }

    fun getIndicatorCurrentValue(): String{
        return series[0].toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val binding: IndicatorRowBinding = IndicatorRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SerieViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = series.size

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) = holder.bind(series.get(position))


}

class SerieViewHolder(private val indicatorRowBinding: IndicatorRowBinding) : RecyclerView.ViewHolder(indicatorRowBinding.root),
    View.OnClickListener {

    private lateinit var serie: Serie


    @SuppressLint("SetTextI18n")
    fun bind(item: Serie) {
        this.serie = item
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        indicatorRowBinding.itemIconLayout.visibility = View.GONE
        indicatorRowBinding.itemUnit.visibility = View.GONE
        indicatorRowBinding.itemName.text = item.value.toString()
        indicatorRowBinding.itemValue.text = simpleDateFormat.format(item.date)
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}