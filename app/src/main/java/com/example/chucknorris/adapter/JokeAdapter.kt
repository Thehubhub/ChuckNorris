package com.example.chucknorris.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorris.databinding.JokesOutlineBinding
import com.example.chucknorris.models.Jokes
import com.example.chucknorris.models.Value

class JokeAdapter (
    private val jokes: MutableList<Value> = mutableListOf(),
    private val onJokeClicked: (joke: Value) -> Unit
): RecyclerView.Adapter<JokesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        return JokesViewHolder(
            JokesOutlineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        onJokeClicked
        )
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int = jokes.size


    fun setNewJoke(newJoke: List<Value>){
        jokes.clear()
        jokes.addAll(newJoke)
        notifyDataSetChanged()
    }

}

class JokesViewHolder(
    private val binding: JokesOutlineBinding,
    private val onJokeClicked: (joke: Value) -> Unit
): RecyclerView.ViewHolder(binding.root){

    fun bind(jokes: Value){
        binding.theTerribleJokes.text = jokes.joke
        binding.root.setOnClickListener{onJokeClicked(jokes)}
    }
}