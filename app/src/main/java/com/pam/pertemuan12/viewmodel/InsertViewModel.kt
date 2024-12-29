package com.pam.pertemuan12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.pertemuan12.model.Mahasiswa
import com.pam.pertemuan12.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertViewModel(private val mhs: MahasiswaRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertMhsState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun isFormValid(): Boolean {
        return uiState.insertUiEvent.run {
            nim.isNotBlank() &&
                    nama.isNotBlank() &&
                    alamat.isNotBlank() &&
                    jenis_kelamin.isNotBlank() &&
                    kelas.isNotBlank() &&
                    angkatan.isNotBlank()
        }
    }

    suspend fun insertMhs() {
        viewModelScope.launch {
            try {
                mhs.insertMahasiswa(uiState.insertUiEvent.toMhs())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val nim: String ="",
    val nama: String ="",
    val alamat: String ="",
    val jenis_kelamin: String ="",
    val kelas: String ="",
    val angkatan: String =""
)

fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenis_kelamin = jenis_kelamin,
    kelas = kelas,
    angkatan = angkatan
)

fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Mahasiswa.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenis_kelamin = jenis_kelamin,
    kelas = kelas,
    angkatan = angkatan
)