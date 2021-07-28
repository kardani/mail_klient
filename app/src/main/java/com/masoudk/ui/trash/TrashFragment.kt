package com.masoudk.ui.trash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.masoudk.ui.R
import com.masoudk.ui.adapter.LoaderStateAdapter
import com.masoudk.ui.adapter.MessagesAdapter
import com.masoudk.ui.base.BaseFragment
import com.masoudk.ui.databinding.FragmentTrashBinding
import com.masoudk.ui.model.Message
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class TrashFragment : BaseFragment<TrashViewModel>(R.layout.fragment_trash, TrashViewModel::class), MessagesAdapter.ClickListener {

    override val viewModel: TrashViewModel by viewModel()
    private val adapter = MessagesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSpecificBinding<FragmentTrashBinding>()?.also{
            it.viewModel = this.viewModel
            it.rvMessages.adapter = adapter.withLoadStateFooter(LoaderStateAdapter{ adapter.retry() })
        }

        lifecycleScope.launch {
            viewModel.messages.collectLatest {
                Timber.d(it.toString())
                adapter.submitData(it)
            }
        }
    }

    override fun click(item: Message) {
        val destination = TrashFragmentDirections.actionTrashFragmentToMessageDetailFragment(item)
        findNavController().navigate(destination)
    }
}