package com.pam.pertemuan12

import android.app.Application
import com.pam.pertemuan12.repository.AppContainer
import com.pam.pertemuan12.repository.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}