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
    private val mahasiswaAPIService: MahasiswaService
) : MahasiswaRepository {

    override suspend fun getMahasiswa(): List<Mahasiswa> {
        try {
            return mahasiswaAPIService.getMahasiswa()
        } catch (e: IOException) {
            throw IOException("Failed to fetch mahasiswa list. Network error occurred.", e)
        }
    }

    override suspend fun getMahasiswaById(nim: String): Mahasiswa {
        try {
            return mahasiswaAPIService.getMahasiswaById(nim)
        } catch (e: IOException) {
            throw IOException("Failed to fetch mahasiswa with NIM: $nim. Network error occurred.", e)
        }
    }

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        try {
            val response = mahasiswaAPIService.insertMahasiswa(mahasiswa)
            if (!response.isSuccessful) {
                throw IOException("Failed to insert mahasiswa. HTTP Status code: ${response.code()}")
            }
        } catch (e: IOException) {
            throw IOException("Failed to insert mahasiswa. Network error occurred.", e)
        }
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        try {
            val response = mahasiswaAPIService.updateMahasiswa(nim, mahasiswa)
            if (!response.isSuccessful) {
                throw IOException("Failed to update mahasiswa with NIM: $nim. HTTP Status code: ${response.code()}")
            }
        } catch (e: IOException) {
            throw IOException("Failed to update mahasiswa. Network error occurred.", e)
        }
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = mahasiswaAPIService.deleteMahasiswa(nim)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete mahasiswa with NIM: $nim. HTTP Status code: ${response.code()}")
            }
        } catch (e: IOException) {
            throw IOException("Failed to delete mahasiswa. Network error occurred.", e)
        }
    }
}
