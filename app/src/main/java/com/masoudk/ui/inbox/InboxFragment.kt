package com.masoudk.ui.inbox

import android.os.Bundle
import android.view.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.masoudk.ui.R
import com.masoudk.ui.adapter.LoaderStateAdapter
import com.masoudk.ui.adapter.MessagesAdapter
import com.masoudk.ui.base.BaseFragment
import com.masoudk.ui.databinding.FragmentInboxBinding
import com.masoudk.ui.model.Message
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class InboxFragment : BaseFragment<InboxViewModel>(R.layout.fragment_inbox, InboxViewModel::class), MessagesAdapter.ClickListener {

    override val viewModel: InboxViewModel by viewModel()

    private val adapter = MessagesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSpecificBinding<FragmentInboxBinding>()?.also{
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.inbox, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.add_message){
            viewModel.simulateReceiveNewMessage()
        }else if(item.itemId == R.id.trash){
            openTrash()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun click(item: Message) {
        val destination = InboxFragmentDirections.actionInboxFragmentToMessageDetailFragment(item)
        findNavController().navigate(destination)
    }

    private fun openTrash(){
        val destination = InboxFragmentDirections.actionInboxFragmentToTrashFragment()
        findNavController().navigate(destination)
    }

}