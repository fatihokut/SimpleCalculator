package com.example.simplecalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.simplecalculator.R
import com.example.simplecalculator.viewmodel.CalculatorViewModel

class SelectOperationFragment : Fragment() {

    private val model: CalculatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_operation, container, false)

        val spinner: Spinner = view.findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.operation_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                model.setActiveOperation(spinner.selectedItem.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        view.findViewById<Button>(R.id.pick_operand_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.select_operation_to_pick_operand)
        }

        return view
    }

}