package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.ComponentActivity

class DevicesActivity : ComponentActivity() {

    private val devices = mutableListOf<Device>()
    private lateinit var adapter: DeviceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        val listViewDevices = findViewById<ListView>(R.id.listViewDevices)

        adapter = DeviceAdapter(this, devices)
        listViewDevices.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            showAddDeviceDialog()
        }

        listViewDevices.setOnItemClickListener { parent, view, position, id ->
            val selectedDevice = devices[position]

            AlertDialog.Builder(this)
                .setTitle("Selecciona una acción")
                .setItems(arrayOf("Modificar", "Eliminar")) { _, which ->
                    when (which) {
                        0 -> showEditDeviceDialog(selectedDevice, position) // Modificar
                        1 -> {
                            // Eliminar
                            AlertDialog.Builder(this)
                                .setTitle("Eliminar Dispositivo")
                                .setMessage("¿Estás seguro de que quieres eliminar '${selectedDevice.name}'?")
                                .setPositiveButton("Eliminar") { _, _ ->
                                    devices.removeAt(position) // Elimina el dispositivo
                                    adapter.notifyDataSetChanged()
                                }
                                .setNegativeButton("Cancelar", null)
                                .show()
                        }
                    }
                }
                .show()
        }

        buttonBack.setOnClickListener {
            finish()  // Regresa al menú principal
        }
    }

    private fun showAddDeviceDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_device, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextDeviceName)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDeviceDescription)

        AlertDialog.Builder(this)
            .setTitle("Agregar Dispositivo")
            .setView(dialogView)
            .setPositiveButton("Agregar") { _, _ ->
                val name = editTextName.text.toString()
                val description = editTextDescription.text.toString()

                if (name.isNotEmpty() && description.isNotEmpty()) {
                    val newDevice = Device(devices.size + 1, name, description)
                    devices.add(newDevice) // Agrega el nuevo dispositivo
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun showEditDeviceDialog(device: Device, position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_device, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextDeviceName)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDeviceDescription)

        editTextName.setText(device.name)
        editTextDescription.setText(device.description)

        AlertDialog.Builder(this)
            .setTitle("Modificar Dispositivo")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val newName = editTextName.text.toString()
                val newDescription = editTextDescription.text.toString()

                if (newName.isNotEmpty() && newDescription.isNotEmpty()) {
                    devices[position] = Device(device.id, newName, newDescription) // Modifica el dispositivo
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}

data class Device(
    val id: Int,
    val name: String,
    val description: String
)