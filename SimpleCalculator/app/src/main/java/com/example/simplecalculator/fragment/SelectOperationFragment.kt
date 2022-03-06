package com.example.simplecalculator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.simplecalculator.R

class SelectOperationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_operation, container, false)

        view.findViewById<Button>(R.id.pick_operand_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.select_operation_to_pick_operand)
        }

        return view
    }

}