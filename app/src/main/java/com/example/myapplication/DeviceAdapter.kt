package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DeviceAdapter(context: Context, private val devices: List<Device>) : ArrayAdapter<Device>(context, 0, devices) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val device = devices[position]

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.device_item, parent, false)

        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewDescription = view.findViewById<TextView>(R.id.textViewDescription)

        textViewName.text = device.name
        textViewDescription.text = device.description

        return view
    }
}