package me.keyo.cb.ui.search

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.view_rating.view.*
import me.keyo.cb.R

class RatingView(
    context: Context,
    attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    var stars = 0F
        set(value) {
            field = value/2
            setStars()
        }
    var count = 0
        set(value) {
            field = value
            setCounts()
        }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_rating, this, true)

        orientation = HORIZONTAL

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.view_rating_attributes, 0, 0)

            stars = typedArray.getFloat(R.styleable.view_rating_attributes_view_rating_stars, 2.5F) /2
            count = typedArray.getInt(R.styleable.view_rating_attributes_view_rating_reviews_count, 123)

            setStars()
            setCounts()

            typedArray.recycle()
        }
    }

    private fun setCounts() {
        view_rating_count_text.text = """($count)"""
    }

    private fun setStars() {
        val noStarImage = AppCompatResources.getDrawable(context, R.drawable.ic_star_unlit)
        val starImage = AppCompatResources.getDrawable(context, R.drawable.ic_star_lit)
        val halfStarImage = AppCompatResources.getDrawable(context, R.drawable.ic_star_half)

        view_rating_star1.setImageDrawable(noStarImage)
        view_rating_star2.setImageDrawable(noStarImage)
        view_rating_star3.setImageDrawable(noStarImage)
        view_rating_star4.setImageDrawable(noStarImage)
        view_rating_star5.setImageDrawable(noStarImage)

        if(stars >= 0.5F && stars < 1.0F) {
            view_rating_star1.setImageDrawable(halfStarImage)
        }

        if(stars >= 1.0F && stars < 1.5F) {
            view_rating_star1.setImageDrawable(starImage)
        }

        if(stars >= 1.5F && stars < 2.0F) {
            view_rating_star1.setImageDrawable(starImage)
            view_rating_star2.setImageDrawable(halfStarImage)
        }

        if(stars >= 2.0F && stars < 2.5F) {
            view_rating_star1.setImageDrawable(starImage)
            view_rating_star2.setImageDrawable(starImage)
            view_rating_star3.setImageDrawable(halfStarImage)
        }

        if(stars >= 2.5F && stars < 3.0F) {
            view_rating_star1.setImageDrawable(starImage)
            view_rating_star2.setImageDrawable(starImage)
            view_rating_star3.setImageDrawable(starImage)
        }

        if(stars >= 3.0F && stars < 3.5F) {
            view_rating_star1.setImageDrawable(starImage)
            view_rating_star2.setImageDrawable(starImage)
            view_rating_star3.setImageDrawable(starImage)
            view_rating_star4.setImageDrawable(halfStarImage)
        }

        if(stars >= 4.0F && stars < 4.5F) {
            view_rating_star1.setImageDrawable(starImage)
            view_rating_star2.setImageDrawable(starImage)
            view_rating_star3.setImageDrawable(starImage)
            view_rating_star4.setImageDrawable(starImage)
        }

        if(stars >= 4.5F && stars < 5.9F) {
            view_rating_star1.setImageDrawable(starImage)
            view_rating_star2.setImageDrawable(starImage)
            view_rating_star3.setImageDrawable(starImage)
            view_rating_star4.setImageDrawable(starImage)
            view_rating_star5.setImageDrawable(halfStarImage)
        }

        if(stars >= 5.9F) {
            view_rating_star1.setImageDrawable(starImage)
            view_rating_star2.setImageDrawable(starImage)
            view_rating_star3.setImageDrawable(starImage)
            view_rating_star4.setImageDrawable(starImage)
            view_rating_star5.setImageDrawable(starImage)
        }
    }
}