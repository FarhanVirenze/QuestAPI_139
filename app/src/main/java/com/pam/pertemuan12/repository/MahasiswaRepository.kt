package com.pam.pertemuan12.repository

import com.pam.pertemuan12.model.Mahasiswa
import com.pam.pertemuan12.service_api.MahasiswaService
import java.io.IOException

interface MahasiswaRepository {
    suspend fun getMahasiswa(): List<Mahasiswa>
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa(nim: String)
    suspend fun getMahasiswaById(nim: String): Mahasiswa
}

class NetworkMahasiswaRepository(
    private val MahasiswaAPIService: MahasiswaService
): MahasiswaRepository {
    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        MahasiswaAPIService.insertMahasiswa(mahasiswa)
    }
    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        MahasiswaAPIService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = MahasiswaAPIService.deleteMahasiswa(nim)
            if (!response.isSuccessful) {
                throw IOException("failed to delete mahasiswa. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getMahasiswa(): List<Mahasiswa> = MahasiswaAPIService.getMahasiswa()
    override suspend fun getMahasiswaById(nim: String): Mahasiswa {
        return MahasiswaAPIService.getMahasiswaById(nim)
    }
}