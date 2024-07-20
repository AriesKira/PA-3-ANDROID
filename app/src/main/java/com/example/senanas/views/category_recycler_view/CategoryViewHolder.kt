package com.example.senanas.views.category_recycler_view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.R
import com.example.senanas.model.CategoryDto

class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private var nameTextView : TextView

    init {
        this.nameTextView = itemView.findViewById(R.id.textViewCategoryName)
    }

    fun bind(category: CategoryDto) {
        this.nameTextView.text = category.name
    }
}