package com.pam.pertemuan12.service_api

import com.pam.pertemuan12.model.Mahasiswa
import retrofit2.Response
import retrofit2.http.*

interface MahasiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("bacamahasiswa.php")
    suspend fun getMahasiswa(): List<Mahasiswa>

    @GET("baca1mahasiswa.php")
    suspend fun getMahasiswaById(@Query("nim") nim: String): Mahasiswa

    @POST("insertmahasiswa.php")
    suspend fun insertMahasiswa(@Body mahasiswa: Mahasiswa): Response<Void>

    @PUT("editmahasiswa.php")
    suspend fun updateMahasiswa(
        @Query("nim") nim: String,
        @Body mahasiswa: Mahasiswa
    ): Response<Void>

    @DELETE("deletemahasiswa.php")
    suspend fun deleteMahasiswa(@Query("nim") nim: String): Response<Void>
}
