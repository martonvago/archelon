package com.martonvago.archelon.ui.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martonvago.archelon.databinding.SavedSurveyBinding
import com.martonvago.archelon.entity.SurveyWithEvents

class SavedSurveysAdapter(
    private var surveys: List<SurveyWithEvents>? = emptyList(),
): RecyclerView.Adapter<SavedSurveysAdapter.ViewHolder>() {

    // A view holder for holding the individual saved surveys
    inner class ViewHolder(val binding: SavedSurveyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(survey: SurveyWithEvents) {
            binding.surveyWithEvents = survey
            binding.executePendingBindings()
        }
    }

    // Create a view holder with the binding specific to saved surveys
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = SavedSurveyBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    // Bind the survey at the given position to the view holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        surveys?.get(position)?.let { viewHolder.bind(it) }
    }

    override fun getItemCount() = surveys?.size ?: 0

    fun setSurveys(newSurveys: List<SurveyWithEvents>) {
        surveys = newSurveys
        notifyDataSetChanged()
    }
}