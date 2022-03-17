package com.example.schoolproject2.ui.BottomSheet

import android.content.Context
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolproject2.R

internal class BottomSheetAdapter( val context: Context, val list: MutableList<String>, val iList: IListSelector) :
    RecyclerView.Adapter<BottomSheetAdapter.viewHolder>() {


    internal inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.list_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_bottomsheet, parent, false)
        return viewHolder(itemView)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.text = list[position]
        holder.title.setOnClickListener{
            iList.itemSelected(position)
          }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}