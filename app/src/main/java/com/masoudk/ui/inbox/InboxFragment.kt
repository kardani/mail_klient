package com.masoudk.ui.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.masoudk.ui.base.BaseFragment
import com.masoudk.ui.databinding.FragmentInboxBinding
import com.masoudk.ui.model.Message
import org.koin.android.viewmodel.ext.android.viewModel

class InboxFragment : BaseFragment(), UsersClickListener {

    val viewModel: InboxViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentInboxBinding = FragmentInboxBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.usersRV.adapter = MessagesAdapter(this)

        return binding.root

    }

    override fun click(user: Message) {
        val destination = InboxFragmentDirections.actionUsersFragmentToUserDetailFragment(user)
        findNavController().navigate(destination)
    }
}