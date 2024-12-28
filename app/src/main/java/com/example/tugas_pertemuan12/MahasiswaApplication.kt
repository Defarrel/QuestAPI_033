package com.example.tugas_pertemuan12

import android.app.Application
import com.example.tugas_pertemuan12.di.AppContainer
import com.example.tugas_pertemuan12.di.MahasiswaContainer

class MahasiswaApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}