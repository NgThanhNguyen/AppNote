package com.example.noteapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.R
import com.example.noteapp.UserData
import com.google.firebase.database.*


class ReadNote : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: StaggeredGridLayoutManager
    private lateinit var database : DatabaseReference
    private lateinit var noteList : ArrayList<UserData>
    private lateinit var backbtn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_note, container, false)
    }

/*    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backbtn = view.findViewById(R.id.btnback)
        recyclerView = view.findViewById(R.id.note_recycler)
        layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        noteList = arrayListOf()
        getNoteData()
        backButton()
    }

    private fun getNoteData() {
        database = FirebaseDatabase.getInstance("https://noteapp-b945c-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Note")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val note = userSnapshot.getValue(UserData::class.java)
                        noteList.add(note!!)
                    }
                    recyclerView.adapter = NoteAdapter(noteList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    private fun backButton(){
        backbtn.setOnClickListener {
            if(fragmentManager?.backStackEntryCount!! > 0) {
                fragmentManager?.popBackStack("AddNote", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }*/
}