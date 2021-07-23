package com.masoudk.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.masoudk.ui.base.BaseFragment
import com.masoudk.ui.databinding.FragmentUserDetailsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class UserDetailFragment : BaseFragment() {

    val viewModel: UserViewModel by viewModel()

    val args : UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentUserDetailsBinding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.setUser(args.user)

        return binding.root

    }

}