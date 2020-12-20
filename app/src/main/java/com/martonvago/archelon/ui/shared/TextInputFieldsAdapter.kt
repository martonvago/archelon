package com.martonvago.archelon.ui.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.martonvago.archelon.databinding.TextInputFieldBinding
import com.martonvago.archelon.ui.createsurvey.TextInputField

data class TextInputComponent(
    val targetField: TextInputField,
    val label: String
)

class TextInputFieldsAdapter(
    private val textInputs: List<TextInputComponent>,
    private val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<TextInputFieldsAdapter.ViewHolder>() {

    // A view holder for holding the individual text input fields
    inner class ViewHolder(val binding: TextInputFieldBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(textInputComponent: TextInputComponent) {
            binding.textInputComponent = textInputComponent
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

    // Bind the input field at the given position to the view holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(textInputs[position])
    }

    override fun getItemCount() = textInputs.size
}