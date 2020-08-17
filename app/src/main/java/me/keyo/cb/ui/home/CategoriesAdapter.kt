package me.keyo.cb.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.keyo.cb.R
import me.keyo.cb.data.model.ProductCategory

class CategoriesAdapter(var categories: List<ProductCategory>): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    class CategoriesViewHolder(val view: View): RecyclerView.ViewHolder(view)

    var onItemClick: ((ProductCategory)->Unit) ?= null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return CategoriesViewHolder(itemView)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item_category_image = holder.view.findViewById<ImageView>(R.id.item_category_image)
        val item_category_text = holder.view.findViewById<TextView>(R.id.item_category_text)

        holder.view.setOnClickListener {
            onItemClick?.invoke(categories[position])
        }


        val bindingItem: ProductCategory = categories[position]
        item_category_text.text = bindingItem.name
        item_category_image.setImageDrawable(bindingItem.getImageDrawable(holder.view.context))

    }

    override fun onViewDetachedFromWindow(holder: CategoriesViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.view.setOnClickListener(null)
    }


}
