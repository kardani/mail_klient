package com.masoudk.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.masoudk.ui.base.BaseFragment
import com.masoudk.ui.databinding.FragmentMessageDetailsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MessageDetailFragment : BaseFragment() {

    private val detailViewModel: MessageDetailViewModel by viewModel()

    private val args : MessageDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentMessageDetailsBinding = FragmentMessageDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel

        activity?.title = args.message.subject

        detailViewModel.setUser(args.message)

        detailViewModel.moveBackEvent.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            if(it){
                moveBackToPreviousScreen()
            }
        })

        return binding.root

    }

    private fun moveBackToPreviousScreen(){
        findNavController().popBackStack()
    }

}