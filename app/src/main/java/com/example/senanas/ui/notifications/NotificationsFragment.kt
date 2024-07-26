/*
package com.example.senanas.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.senanas.data.UserDataLayerSingleton
import com.example.senanas.databinding.FragmentHomeBinding

class NotificationsFragment : Fragment() {

    private var _binding:FragmentHomeBinding? = null
    private lateinit var userDataLayer: UserDataLayerSingleton

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        //binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //notificationsViewModel.text.observe(viewLifecycleOwner) {
          //  textView.text = it
        //}


        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initProfileViewModel()
        userDataLayer.getProfileViewModel().getUserInfo("BEARER eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImVtYWlsIjoidXNlckBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcyMTE0MjEyOH0.WNA7sGEjlF-f0uTZa1PUIrBLZPrjEdDLJ61UXAeCDyU")
        userDataLayer.getProfileViewModel().userData.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = {

                },
                onFailure = { throwable ->
                    println("Login failed: ${throwable.message}")
                }
            )
        }

        //saveButton.setOnClickListener {
            //val updateUserDto = UpdateUserDto(firstname = firstnameEditText.text.toString(), lastname = lastnameEditText.text.toString(), email = emailEditText.text.toString())
            //userDataLayer.getProfileViewModel().updateUserInfo("BEARER eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImVtYWlsIjoidXNlckBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcyMTE0MjEyOH0.WNA7sGEjlF-f0uTZa1PUIrBLZPrjEdDLJ61UXAeCDyU",updateUserDto)
        //}


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
*/
