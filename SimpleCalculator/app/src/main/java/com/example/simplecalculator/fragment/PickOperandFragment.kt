package com.example.simplecalculator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.simplecalculator.R

class PickOperandFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pick_operand, container, false)

        view.findViewById<Button>(R.id.add_operand_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.pick_operand_to_pick_operand)
        }

        view.findViewById<Button>(R.id.show_result_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.pick_operand_to_show_result)
        }

        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.pick_operand_to_select_operation)
        }

        return view
    }
}