/*
package com.example.senanas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.R
import com.example.senanas.data.UserDataLayerSingleton
import com.example.senanas.databinding.FragmentHomeBinding
import com.example.senanas.model.CategoryDto
import com.example.senanas.views.category_recycler_view.CategoryListAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var userDataLayer: UserDataLayerSingleton
    private lateinit var recyclerViewCategories: RecyclerView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerViewCategories = root.findViewById(R.id.recyclerViewCategories)


        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initHomeViewModel()
        userDataLayer.getHomeViewModel().getCategories("BEARER eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImVtYWlsIjoidXNlckBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcyMTE0MjEyOH0.WNA7sGEjlF-f0uTZa1PUIrBLZPrjEdDLJ61UXAeCDyU")
        userDataLayer.getHomeViewModel().categories.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { categories ->
                categories?.let {

                }
            }
        })



        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}*/
