package com.martonvago.archelon.ui.createsurvey.dialogs.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.martonvago.archelon.databinding.SelectOptionBinding
import com.martonvago.archelon.entity.Displayable

/**
 * A [RecyclerView] adapter for displaying a list of options for a select field, where the
 * options are [Displayable] enums.
 */
class OptionsAdapter(
    val selectOptionArgs: SelectOptionArgs,
    val navController: NavController
): RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    // A view holder for holding individual select options
    inner class ViewHolder(val binding: SelectOptionBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.selectItem.setOnClickListener {
                val newOption = binding.option
                if (newOption != null) {
                    selectOptionArgs.targetField.setContentValue(newOption)
                    navController.navigateUp()
                }
            }
        }

        fun bind(option: Displayable) {
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

    // Bind the layout variables of the option at the given position
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(selectOptionArgs.selectOptions[position])
    }

    override fun getItemCount() = selectOptionArgs.selectOptions.size
}