package com.example.testkoinkotlin.di
import androidx.room.Room
import com.example.testkoinkotlin.persistence.NoteDatabase
import com.example.testkoinkotlin.repository.NoteRepository
import com.example.testkoinkotlin.ui.NoteViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var appModule: Module = module {

    factory{ NoteRepository(get()) }
    single { Room.databaseBuilder(get(), NoteDatabase::class.java, "note_database").build() }
    single { get<NoteDatabase>().noteDao()}
    }

val viewModelModule = module {
    viewModel {
        NoteViewModel(get())
    }


}