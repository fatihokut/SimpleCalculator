package com.example.simplecalculator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.simplecalculator.R

class ShowResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_result, container, false)

        view.findViewById<Button>(R.id.new_operation_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.show_result_to_select_operation)
        }

        return view
    }

}