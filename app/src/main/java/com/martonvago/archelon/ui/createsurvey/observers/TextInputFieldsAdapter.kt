package com.martonvago.archelon.ui.createsurvey.observers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.martonvago.archelon.databinding.TextInputFieldBinding
import com.martonvago.archelon.ui.createsurvey.TextInputField

/**
 * Passed from fragments with text input fields to [TextInputFieldsAdapter].
 */
data class TextInputFieldArgs(
    val targetField: TextInputField,
    val label: String
)

/**
 * A [RecyclerView] adapter for displaying a list of text input fields.
 */
class TextInputFieldsAdapter(
    private val textInputs: List<TextInputFieldArgs>,
    private val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<TextInputFieldsAdapter.ViewHolder>() {

    // A view holder for holding the individual text input fields
    inner class ViewHolder(val binding: TextInputFieldBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(textInputFieldArgs: TextInputFieldArgs) {
            binding.textInputFieldArgs = textInputFieldArgs
            binding.executePendingBindings()
        }
    }

    // Create a view holder with the binding specific to input fields
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = TextInputFieldBinding.inflate(inflater, viewGroup, false)
        binding.lifecycleOwner = lifecycleOwner
        return ViewHolder(binding)
    }

    // Bind the layout variables of the input field at the given position
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(textInputs[position])
    }

    override fun getItemCount() = textInputs.size
}