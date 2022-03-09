package com.example.simplecalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.simplecalculator.R
import com.example.simplecalculator.databinding.FragmentShowResultBinding
import com.example.simplecalculator.viewmodel.CalculatorViewModel

class ShowResultFragment : Fragment() {

    private val model: CalculatorViewModel by activityViewModels()

    private var showResultBinding: FragmentShowResultBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShowResultBinding.inflate(inflater, container, false)
        showResultBinding = binding

        model.result.observe(viewLifecycleOwner) { newResult ->
            binding.result.text = newResult
        }

        binding.newOperationBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.show_result_to_select_operation)
        }

        return binding.root
    }

    override fun onDestroyView() {
        showResultBinding = null
        super.onDestroyView()
    }

}