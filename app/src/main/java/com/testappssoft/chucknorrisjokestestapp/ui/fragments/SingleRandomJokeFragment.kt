package com.testappssoft.chucknorrisjokestestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.testappssoft.chucknorrisjokestestapp.databinding.FragmentSingleRandomJokeBinding
import com.testappssoft.chucknorrisjokestestapp.viewmodels.SingleRandomJokeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleRandomJokeFragment : Fragment() {

    private val viewModel: SingleRandomJokeFragmentViewModel by viewModels()

    private var _binding: FragmentSingleRandomJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSingleRandomJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.jokeText.observe(viewLifecycleOwner, Observer { joke ->
            binding.jokeTextView.text = joke
        })

        viewModel.errorText.observe(viewLifecycleOwner, Observer { error ->
            binding.jokeTextView.text = error
        })

        binding.btnRefresh.setOnClickListener {
            viewModel.fetchJoke()
        }

        viewModel.fetchJoke()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
