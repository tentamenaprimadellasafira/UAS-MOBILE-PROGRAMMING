package com.example.kalkulatorgizi

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class KebutuhanKaloriActivity : AppCompatActivity() {

    private lateinit var beratEdit: EditText
    private lateinit var tinggiEdit: EditText
    private lateinit var umurEdit: EditText
    private lateinit var jenisKelaminSpinner: Spinner
    private lateinit var aktivitasSpinner: Spinner
    private lateinit var btnHitung: Button
    private lateinit var hasilText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kebutuhan_kalori)

        beratEdit = findViewById(R.id.editBerat)
        tinggiEdit = findViewById(R.id.editTinggi)
        umurEdit = findViewById(R.id.editUmur)
        jenisKelaminSpinner = findViewById(R.id.spinnerJenisKelamin)
        aktivitasSpinner = findViewById(R.id.spinnerAktivitas)
        btnHitung = findViewById(R.id.btnHitung)
        hasilText = findViewById(R.id.txtHasil)

        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Laki-laki", "Perempuan"))
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jenisKelaminSpinner.adapter = genderAdapter

        val aktivitasAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Ringan", "Sedang", "Berat"))
        aktivitasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        aktivitasSpinner.adapter = aktivitasAdapter

        btnHitung.setOnClickListener {
            val berat = beratEdit.text.toString().toDoubleOrNull() ?: 0.0
            val tinggi = tinggiEdit.text.toString().toDoubleOrNull() ?: 0.0
            val umur = umurEdit.text.toString().toIntOrNull() ?: 0
            val jenisKelamin = jenisKelaminSpinner.selectedItem.toString()
            val aktivitas = aktivitasSpinner.selectedItem.toString()

            val bmr = if (jenisKelamin == "Laki-laki") {
                66.5 + (13.75 * berat) + (5.003 * tinggi) - (6.75 * umur)
            } else {
                655.1 + (9.563 * berat) + (1.850 * tinggi) - (4.676 * umur)
            }

            val faktor = when (aktivitas) {
                "Sedang" -> 1.55
                "Berat" -> 1.725
                else -> 1.2
            }

            val hasil = bmr * faktor
            hasilText.text = "Kebutuhan Kalori Anda: %.2f kcal".format(hasil)
        }
    }
}
