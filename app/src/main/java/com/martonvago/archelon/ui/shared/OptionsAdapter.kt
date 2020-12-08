package com.martonvago.archelon.ui.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martonvago.archelon.databinding.SelectOptionBinding
import com.martonvago.archelon.entity.Displayable

class OptionsAdapter<T: Displayable>(
    private val options: Array<T>,
    val updateFormField: (Displayable) -> Unit
): RecyclerView.Adapter<OptionsAdapter<T>.ViewHolder>() {

    // A view holder for holding the individual select options
    inner class ViewHolder(val binding: SelectOptionBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.selectItem.setOnClickListener {
                val newOption = binding.option
                if (newOption != null) {
                    updateFormField(newOption)
                }
            }
        }

        fun bind(option: T) {
            binding.option = option
            binding.executePendingBindings()
        }
    }

    // Create a view holder with the binding specific to options in the select
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = SelectOptionBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    // Bind the option at the given position to the view holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(options[position])
    }

    override fun getItemCount() = options.size
}