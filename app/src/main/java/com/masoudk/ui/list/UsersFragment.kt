package com.masoudk.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.masoudk.repository.model.Message
import com.masoudk.ui.UsersAdapter
import com.masoudk.ui.UsersClickListener
import com.masoudk.ui.base.BaseFragment
import com.masoudk.ui.databinding.FragmentUsersBinding
import org.koin.android.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment(), UsersClickListener {

    val viewModel: UsersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentUsersBinding = FragmentUsersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.usersRV.adapter = UsersAdapter(this)

        return binding.root

    }

    override fun click(user: Message) {
        val destination = UsersFragmentDirections.actionUsersFragmentToUserDetailFragment(user)
        findNavController().navigate(destination)
//        TODO("Not yet implemented")
//        val intent = Intent(this, UserActivity::class.java)
//        intent.putExtra("user", Gson().toJson(user))
//        startActivity(intent)
    }
}