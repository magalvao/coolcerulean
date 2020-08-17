package me.keyo.cb.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,

    @SerializedName("productName")
    val name: String,

    @SerializedName("reviewInformation")
    val review: ReviewInformation,

    @SerializedName("USPs")
    val usps: List<String>?,

    @SerializedName("availabilityState")
    val availabilityState: Int,

    @SerializedName("salesPriceIncVat")
    val price: Float,

    @SerializedName("productImage")
    val imageUrl: String,

    @SerializedName("nextDayDelivery")
    val nextDayDelivery: Boolean
)

data class ReviewInformation (
    @SerializedName("reviewSummary")
    val reviewSummary: ReviewSummary
)

data class ReviewSummary (
    @SerializedName("reviewAverage")
    val reviewAverage: Double,
    @SerializedName("reviewCount")
    val reviewCount: Int
)
