package com.martonvago.archelon.ui.createsurvey

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.martonvago.archelon.databinding.SelectFieldBinding
import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.ui.createsurvey.dialogs.select.SelectOptionArgs
import com.martonvago.archelon.ui.shared.setNavigateOnClickListener

/**
 * Passed from fragments with select input fields to [SelectFieldsAdapter].
 */
data class SelectFieldArgs(
    val targetField: SelectField,
    @StringRes val label: Int,
    @StringRes val selectTitle: Int,
    val selectOptions: List<Displayable>,
) {
    val selectOptionArgs: SelectOptionArgs = SelectOptionArgs(targetField, selectOptions, selectTitle)
}

/**
 * A [RecyclerView] adapter for displaying a list of select input fields.
 */
class SelectFieldsAdapter(
    private val selectFieldArgs: List<SelectFieldArgs>,
    private val lifecycleOwner: LifecycleOwner,
    val clearFocus: () -> Unit,
    val navAction: (SelectOptionArgs) -> NavDirections,
): RecyclerView.Adapter<SelectFieldsAdapter.ViewHolder>() {

    // A view holder for holding the individual select input fields
    inner class ViewHolder(val binding: SelectFieldBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(selectFieldArgs: SelectFieldArgs) {
            binding.selectFieldArgs = selectFieldArgs

            // Clicking a select field opens a dialog with the appropriate options
            binding.selectFieldCard.setNavigateOnClickListener(navAction(selectFieldArgs.selectOptionArgs)) {
                // Prevent the screen from scrolling back to the currently focused element in the background.
                // This improves the behaviour of the create survey form, but for more complex forms
                // this will need to be replaced by an actual focus management system
                clearFocus()
            }
            binding.executePendingBindings()
        }
    }

    // Create a view holder with the binding specific to select fields
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = SelectFieldBinding.inflate(inflater, viewGroup, false)
        binding.lifecycleOwner = lifecycleOwner
        return ViewHolder(binding)
    }

    // Bind the layout variables of the select field at the given position
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(selectFieldArgs[position])
    }

    override fun getItemCount() = selectFieldArgs.size
}