package com.pam.pertemuan12

import android.app.Application
import com.pam.pertemuan12.dependenciesinjection.AppContainer
import com.pam.pertemuan12.dependenciesinjection.MahasiswaContainer

class MahasiswaApplications:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}