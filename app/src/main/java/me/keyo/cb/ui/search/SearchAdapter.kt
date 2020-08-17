package me.keyo.cb.ui.search

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.keyo.cb.R
import me.keyo.cb.data.model.Product
import me.keyo.cb.data.model.ProductCategory
import me.keyo.cb.data.model.ProductSearchResponse
import me.keyo.cb.util.resourceloader.RemoteResourceLoader


class SearchAdapter(var productSearchResponse: ProductSearchResponse):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()  {
    private val VIEW_TYPE_FOOTER = 1
    private val VIEW_TYPE_CELL = 2

    var onNextClick: ((Int)->Unit) ?= null
    var onPrevClick: ((Int)->Unit) ?= null

    class SearchViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        if(viewType == VIEW_TYPE_CELL) {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false)

            return SearchViewHolder(itemView)
        } else {
            val pagingView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_paging, parent, false)

            return SearchViewHolder(pagingView)
        }
    }

    override fun getItemCount(): Int = productSearchResponse.products.size+1

    override fun getItemViewType(position: Int): Int {
        if(position == productSearchResponse.products.size)
            return VIEW_TYPE_FOOTER
        else
            return VIEW_TYPE_CELL
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        if(position == productSearchResponse.products.size) {
            val view_paging_prev = holder.view.findViewById<Button>(R.id.view_paging_prev)
            val view_paging_next = holder.view.findViewById<Button>(R.id.view_paging_next)
            val view_paging_text = holder.view.findViewById<TextView>(R.id.view_paging_text)

            view_paging_prev.isEnabled = productSearchResponse.currentPage != 1
            view_paging_next.isEnabled = productSearchResponse.currentPage != productSearchResponse.pageCount

            view_paging_text.text =
                """Page ${productSearchResponse.currentPage} of ${productSearchResponse.pageCount}"""

            view_paging_prev.setOnClickListener {
                onPrevClick?.invoke(productSearchResponse.currentPage - 1)
            }

            view_paging_next.setOnClickListener {
                onNextClick?.invoke(productSearchResponse.currentPage + 1)
            }
        } else {

            val item_search_title = holder.view.findViewById<TextView>(R.id.item_search_title)
            val item_search_rating = holder.view.findViewById<RatingView>(R.id.item_search_rating)
            val item_search_price = holder.view.findViewById<TextView>(R.id.item_search_price)
            val item_search_image = holder.view.findViewById<ImageView>(R.id.item_search_image)
            val item_search_ksp = holder.view.findViewById<LinearLayout>(R.id.item_search_ksp)

            item_search_image.visibility = View.INVISIBLE

            val bindingItem: Product = productSearchResponse.products[position]
            item_search_title.text = bindingItem.name

            item_search_rating.stars = bindingItem.review.reviewSummary.reviewAverage.toFloat()
            item_search_rating.count = bindingItem.review.reviewSummary.reviewCount
            item_search_price.text = String.format("$ %.2f", bindingItem.price).replace(".00", "")

            val job = Job()
            val ioScope = CoroutineScope(Dispatchers.IO + job)
            val uiScope = CoroutineScope(Dispatchers.Main + job)

            ioScope.launch {
                val remoteResource = RemoteResourceLoader()
                val remoteImage: Drawable? = remoteResource.getDrawable(bindingItem.imageUrl, holder.view.context)
                uiScope.launch {
                    item_search_image.visibility = View.VISIBLE
                    item_search_image.setImageDrawable(remoteImage)
                }
            }

            item_search_ksp.removeAllViews()
            bindingItem.usps?.forEach {
                val ksp = LayoutInflater.from(holder.view.context).inflate(R.layout.view_product_description_item, null)
                val kspTextView: TextView = ksp as TextView
                kspTextView.text = it
                item_search_ksp.addView(kspTextView)
            }

            if(bindingItem.nextDayDelivery) {
                val ksp = LayoutInflater.from(holder.view.context).inflate(R.layout.view_product_description_item, null)
                val kspTextView: TextView = ksp as TextView
                kspTextView.text = "Next day delivery!"
                kspTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_delivery, 0, 0, 0);
                kspTextView.setTypeface(kspTextView.typeface, Typeface.BOLD)
                item_search_ksp.addView(kspTextView)
            }
        }






    }



}