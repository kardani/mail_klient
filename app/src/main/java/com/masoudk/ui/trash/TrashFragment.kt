package com.masoudk.ui.trash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.masoudk.ui.adapter.LoaderStateAdapter
import com.masoudk.ui.adapter.MessagesAdapter
import com.masoudk.ui.base.BaseFragment
import com.masoudk.ui.databinding.FragmentTrashBinding
import com.masoudk.ui.model.Message
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class TrashFragment : BaseFragment(), MessagesAdapter.ClickListener {

    val viewModel: TrashViewModel by viewModel()
    private val adapter = MessagesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentTrashBinding = FragmentTrashBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.rvMessages.adapter = adapter.withLoadStateFooter(LoaderStateAdapter{ adapter.retry() })

        lifecycleScope.launch {
            viewModel.messages.collectLatest {
                Timber.d(it.toString())
                adapter.submitData(it)
            }
        }

        return binding.root

    }

    override fun click(item: Message) {
        val destination = TrashFragmentDirections.actionTrashFragmentToMessageDetailFragment(item)
        findNavController().navigate(destination)
    }
}