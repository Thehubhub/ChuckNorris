package com.example.chucknorris.views

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorris.R
import com.example.chucknorris.adapter.JokeAdapter
import com.example.chucknorris.databinding.ActivityMainBinding
import com.example.chucknorris.databinding.FragmentJokeHomeBinding
import com.example.chucknorris.viewmodel.JokesViewModel

class JokeHomeFragment : BaseFragment() {

    private val binding by lazy {
        JokeAdapter()
    }
    private val jokesViewModel: JokesViewModel()

    private val binding by lazy {
        FragmentJokeHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.jokesRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false)
        adapter = jokesAdapter
        }

        jokesViewModel.getSortedJokes()

        binding.randomJoke.setOnClickListener{
            val ranDiaBox = LayoutInflater
                .from(this)
                .inflate(R.layout.random_joke_dialog, null)

            val boxBuilder = AlertDialog.Builder(this)
                .setView(ranDiaBox)
                .setTitle("Here's a random Chuck Norris joke")

            val ranBox = boxBuilder.show()


        }
        return binding.root
    }
}