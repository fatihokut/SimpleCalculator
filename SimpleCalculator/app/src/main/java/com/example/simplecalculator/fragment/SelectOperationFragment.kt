package com.example.simplecalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.simplecalculator.R
import com.example.simplecalculator.databinding.FragmentSelectOperationBinding
import com.example.simplecalculator.viewmodel.CalculatorViewModel

class SelectOperationFragment : Fragment() {

    private val model: CalculatorViewModel by activityViewModels()

    private var selectOperationBinding: FragmentSelectOperationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSelectOperationBinding.inflate(inflater, container, false)
        selectOperationBinding = binding

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.operation_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                model.setActiveOperation(binding.spinner.selectedItem.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.pickOperandBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.select_operation_to_pick_operand)
        }

        return binding.root
    }

    override fun onDestroyView() {
        selectOperationBinding = null
        super.onDestroyView()
    }

}