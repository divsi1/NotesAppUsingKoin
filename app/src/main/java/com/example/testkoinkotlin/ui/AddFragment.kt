package com.example.testkoinkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.testkoinkotlin.R
import com.example.testkoinkotlin.persistence.Note
import kotlinx.android.synthetic.main.fragment_add.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : Fragment() {

//    @Inject
//    lateinit var viewmodelProviderFactory: ViewModelProviderFactory
//
//    lateinit var noteViewModel: NoteViewModel by inject<NoteViewModel>()

    private val noteViewModel by viewModel<NoteViewModel>()

    // Method #1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    // Method #2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    //    setupViewModel()

        btnAdd.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.container).popBackStack()
        }
    }

    // Method #3
    private fun saveNoteToDatabase() {

        //Save Notes WHEN app is minimized or back button is clicked [#onPause method will be called]
        //Save Notes WHEN save button is cliked


        /* VALIDATIONS FOR SAVING NOTES

        1)  if TITLE IS NULL -- Title = "Empty Title"

        2)  if all the property of notes is null i.e title , description , color and tag
        then note will note will saved
        show toast "Note is discarded"

         */

        (activity as MainActivity?)?.showFloatingButton()

        if (validations()) {
            Toast.makeText(activity, "Note is saved", Toast.LENGTH_SHORT).show()
            saveNote()
        } else
            Toast.makeText(activity, "Note is Discarded", Toast.LENGTH_SHORT).show()

    }

    // Method #4
    override fun onDestroyView() {
        super.onDestroyView()
        saveNoteToDatabase()
    }


    // Method #5
    private fun saveNote() {
        val note = Note(0,addTitle.text.toString(),addDescription.text.toString(),addTag.text.toString())

        //If title is null set Empty Title
        if (addTitle.text.isNullOrEmpty()) {
           note.title = "Empty Title"

            //Call viewmodel to save the data
            noteViewModel.insert(note)

        }else{
            //Call viewmodel to save the data
            noteViewModel.insert(note)
        }
    }

    // Method #6
    fun validations(): Boolean {
        return !(addTitle.text.isNullOrEmpty()
                && addDescription.text.isNullOrEmpty()
                && addTag.text.isNullOrEmpty())
    }


    // Method #7
//    private fun setupViewModel() {
//        noteViewModel = ViewModelProvider(this,viewmodelProviderFactory).get(NoteViewModel::class.java)
//    }
}