package com.example.senanas.views.category_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.R
import com.example.senanas.NavigateOnClickLListener
import com.example.senanas.model.CategoryDto

class CategoryListAdapter(
    var categoryList: List<CategoryDto>,
    private val todoClickHandler: NavigateOnClickLListener
): RecyclerView.Adapter<CategoryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(categoryView)
    }

    override fun getItemCount(): Int {
        return this.categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = this.categoryList[position] // Get the data at the right position
        holder.bind(currentCategory)
        holder.itemView.setOnClickListener {
            todoClickHandler.navigate(null)
        }

    }

}