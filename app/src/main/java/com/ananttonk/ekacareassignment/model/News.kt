package com.ananttonk.ekacareassignment.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String
) : Parcelable {
    @Parcelize
    @Entity(tableName = "news")
    data class Source(
        @SerializedName("category")
        val category: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("description")
        val description: String,
        @PrimaryKey
        @SerializedName("id")
        val id: String,
        @SerializedName("language")
        val language: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    ) : Parcelable
}