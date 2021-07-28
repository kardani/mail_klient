package com.masoudk.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM: ViewModel>(@LayoutRes val layout: Int, private val viewModelClass: KClass<VM>) : Fragment() {

    open var binding: ViewDataBinding? = null

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner

        return binding?.root ?: inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding?.setVariable(BR.viewModel, viewModel)
    }

    inline fun <reified specific : ViewDataBinding> getSpecificBinding() = binding as? specific

}