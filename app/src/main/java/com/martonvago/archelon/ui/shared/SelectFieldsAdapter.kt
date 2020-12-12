package com.martonvago.archelon.ui.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.martonvago.archelon.databinding.SelectFieldBinding
import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.ui.createsurvey.SelectArgs
import com.martonvago.archelon.ui.createsurvey.SelectField

data class SelectComponent(
    val targetField: SelectField,
    @StringRes val label: Int,
    @StringRes val selectTitle: Int,
    val selectOptions: List<Displayable>,
) {
    val selectArgs: SelectArgs = SelectArgs(targetField, selectOptions, selectTitle)
}

class SelectFieldsAdapter(
    private val selectComponents: List<SelectComponent>,
    val navAction: (SelectArgs) -> NavDirections,
    private val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<SelectFieldsAdapter.ViewHolder>() {

    // A view holder for holding the individual select input fields
    inner class ViewHolder(val binding: SelectFieldBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(selectComponent: SelectComponent) {
            binding.selectComponent = selectComponent
            binding.selectFieldCard.setNavigateOnClickListener(navAction(selectComponent.selectArgs))
            binding.executePendingBindings()
        }
    }

    // Create a view holder with the binding specific to the select fields
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = SelectFieldBinding.inflate(inflater, viewGroup, false)
        binding.lifecycleOwner = lifecycleOwner
        return ViewHolder(binding)
    }

    // Bind the select field at the given position to the view holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(selectComponents[position])
    }

    override fun getItemCount() = selectComponents.size
}