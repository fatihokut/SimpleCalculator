package com.example.simplecalculator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.simplecalculator.R
import com.example.simplecalculator.viewmodel.CalculatorViewModel

class PickOperandFragment : Fragment() {

    private val model: CalculatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pick_operand, container, false)

        val safeArgs: PickOperandFragmentArgs by navArgs()
        val selectedOperation = safeArgs.selectedOperation

        val textView: TextView = view.findViewById(R.id.textView) as TextView
        textView.text = selectedOperation

        view.findViewById<Button>(R.id.add_operand_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.pick_operand_to_pick_operand)
        }

        view.findViewById<Button>(R.id.show_result_btn).setOnClickListener {
            model.performOperation(selectedOperation.toString(), "10", "0.5")
            Navigation.findNavController(view).navigate(R.id.pick_operand_to_show_result)
        }

        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.pick_operand_to_select_operation)
        }

        return view
    }
}