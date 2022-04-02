package com.example.chucknorris.views

import androidx.fragment.app.Fragment
import com.example.chucknorris.adapter.JokeAdapter
import com.example.chucknorris.viewmodel.JokesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BaseFragment: Fragment() {
    protected val jokesViewModel: JokesViewModel by sharedViewModel()
    protected val jokesAdapter by lazy {
        JokeAdapter()
    }
}