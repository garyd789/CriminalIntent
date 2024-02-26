package com.example.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.ListItemCrimeBinding
import com.example.criminalintent.databinding.ListItemSeriousCrimeBinding

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show() }
    }
}
class SeriousCrimeHolder(
    private val binding: ListItemSeriousCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "Serious ${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show() }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //create two types of crimes
    private val normalCrime = 0
    private val seriousCrime = 1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (getItemViewType(viewType) == 1) {
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            return CrimeHolder(binding)
        }
        else {
            val binding = ListItemSeriousCrimeBinding.inflate(inflater, parent, false)
            return SeriousCrimeHolder(binding)
        }


    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        (holder as? CrimeHolder)?.let {
            it.bind(crime)
        }
        (holder as? SeriousCrimeHolder)?.let {
            it.bind(crime)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) seriousCrime else normalCrime
    }
    override fun getItemCount() = crimes.size
}