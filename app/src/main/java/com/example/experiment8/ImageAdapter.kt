package com.example.experiment8

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ImageAdapter(
    private val context: Context,
    private val images: IntArray,
    private val names: Array<String>
) : BaseAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view = convertView ?: LayoutInflater
            .from(context)
            .inflate(
                R.layout.grid_item,
                parent,
                false
            )

        val imageView =
            view.findViewById<ImageView>(
                R.id.imageView
            )

        val textView =
            view.findViewById<TextView>(
                R.id.textView
            )

        imageView.setImageResource(
            images[position]
        )

        textView.text = names[position]

        return view
    }
}