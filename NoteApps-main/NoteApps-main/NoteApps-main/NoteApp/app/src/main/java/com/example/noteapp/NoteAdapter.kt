package com.example.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private var noteList: ArrayList<UserData>,
                  private val onItemClick:(note : UserData) -> Unit
                  ): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun clear() {
        noteList.clear()
        notifyDataSetChanged()
    }
    inner class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(note: UserData){
            itemView.text_title.text = note.title
            itemView.text_date.text = note.date
            itemView.setOnClickListener{
                onItemClick.invoke(note)
            }
        }
    }

}