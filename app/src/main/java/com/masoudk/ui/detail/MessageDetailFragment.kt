package com.masoudk.ui.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.masoudk.ui.R
import com.masoudk.ui.base.BaseFragment
import com.masoudk.ui.databinding.FragmentMessageDetailsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MessageDetailFragment : BaseFragment<MessageDetailViewModel>(R.layout.fragment_message_details, MessageDetailViewModel::class) {

    override val viewModel: MessageDetailViewModel by viewModel()

    private val args : MessageDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = args.message.subject

        getSpecificBinding<FragmentMessageDetailsBinding>()?.also {
            it.viewModel = this.viewModel
        }

        viewModel.setUser(args.message)

        viewModel.moveBackEvent.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            if(it){
                moveBackToPreviousScreen()
            }
        })

    }

    private fun moveBackToPreviousScreen(){
        findNavController().popBackStack()
    }

}