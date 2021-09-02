package com.example.noteapp.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.noteapp.R
import com.example.noteapp.UserData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class AddNote : Fragment() {
    private val IMAGE_PICK_CODE = 1000
    private val PERMISSION_CODE = 1001

    private lateinit var database : DatabaseReference
    private lateinit var submitbtn : Button
    private lateinit var date : TextView
    private lateinit var title : EditText
    private lateinit var content : EditText
    private lateinit var backBtn: ImageView
    private lateinit var priority: Button
    private lateinit var imageBtn: ImageView
    private lateinit var imageNote: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        implement()

        setPrior()
    }

    private fun implement() {
        // submit button

        submitbtn.setOnClickListener {
            val Date =date.text.toString()
            val Title =title.text.toString()
            val Content =content.text.toString()
            val Priority = priority.text.toString()

/*            database = FirebaseDatabase
                .getInstance("https://noteapp-b945c-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Note")*/
            database = FirebaseDatabase.getInstance("https://note-app-d7239-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Note")
            val userData = UserData(Date,Title,Content,Priority)
            database.child(Title).setValue(userData).addOnSuccessListener {
                title.text.clear()
                content.text.clear()
                priority.text = "Level 1"

            }.addOnFailureListener {

            }
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_layout_id, HomeFragment())
                addToBackStack(null)
                commit()
            }
        }

        // back button
        backBtn.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_layout_id, HomeFragment())
                addToBackStack(null)
                commit()
            }
        }

        // add image
        imageBtn.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(this.context?.let { it1 ->
                        ContextCompat.checkSelfPermission(
                            it1,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    } == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions,PERMISSION_CODE)
                }
                else {
                    pickImageFromGallery()
                }
            }
            else {
                pickImageFromGallery()
            }
            imageNote.visibility = View.VISIBLE
        }
    }

    private fun init() {
        submitbtn = view?.findViewById(R.id.submitbtn)!!
        date = view?.findViewById(R.id.date)!!
        title = view?.findViewById(R.id.title)!!
        content = view?.findViewById(R.id.content)!!
        backBtn = view?.findViewById(R.id.back_button)!!
        priority = view?.findViewById(R.id.priority)!!
        imageBtn = view?.findViewById(R.id.add_image)!!
        imageNote = view?.findViewById(R.id.image_note)!!
        date.text = getCurrentDate()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                }
                else {
                    Toast.makeText(this.context,"Permission denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageNote.setImageURI(data?.data)
        }
    }


    private fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }

    private fun setPrior(){
        val popupMenu = PopupMenu(requireContext(),priority)
        popupMenu.menu.add(Menu.NONE , 0, 0, "Level 1")
        popupMenu.menu.add(Menu.NONE , 1, 1, "Level 2")
        popupMenu.menu.add(Menu.NONE , 2, 2, "Level 3")
        popupMenu.setOnMenuItemClickListener {
            val id = it.itemId
            if(id == 0){
                priority.text = "Level 1"
            }
            else if (id == 1){
                priority.text = "Level 2"
            }

            else if (id == 2){
                priority.text = "Level 3"
            }
            false
        }
        priority.setOnClickListener {
            popupMenu.show()
        }
    }
}