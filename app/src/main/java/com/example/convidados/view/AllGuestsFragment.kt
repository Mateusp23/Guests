package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.databinding.FragmentAllBinding
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private var _binding: FragmentAllBinding? = null

    private val mAdapter: GuestAdapter = GuestAdapter()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests) // obter a recycler

        recycler.layoutManager = LinearLayoutManager(context) // definir layout

        // definir um adapter
        recycler.adapter = mAdapter

        observer()

        allGuestsViewModel.load()

        return root
    }

    private fun observer(){
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuest(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}