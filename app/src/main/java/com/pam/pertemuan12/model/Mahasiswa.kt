package com.pam.pertemuan12.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,
    @SerialName(value = "jenis_kelamin")
    val jenis_kelamin: String,
    val kelas: String,
    val angkatan: String,
)
