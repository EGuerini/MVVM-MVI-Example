package com.eguerini.veritrancodeexercise.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.eguerini.veritrancodeexercise.R
import com.eguerini.veritrancodeexercise.databinding.FeaturesFragmentLayoutBinding
import com.eguerini.veritrancodeexercise.di.annotation.Choose
import com.eguerini.veritrancodeexercise.model.entities.ClientModel
import com.eguerini.veritrancodeexercise.ui.viewmodel.FeaturesViewModel
import com.eguerini.veritrancodeexercise.ui.viewmodel.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FeaturesFragment: DaggerFragment() {

    private lateinit var binding: FeaturesFragmentLayoutBinding

    private lateinit var viewModel: FeaturesViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var featureFragmentActionObserver: FeatureFragmentActionObserver

    @Inject @Choose("emanuel")
    lateinit var destinyClient: ClientModel
    lateinit var francisco: ClientModel

    companion object{
        fun getInstance(bundle: Bundle?): FeaturesFragment {
            val fragment = FeaturesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if(requireActivity() is FeatureFragmentActionObserver){
            featureFragmentActionObserver = requireActivity() as FeatureFragmentActionObserver
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FeaturesFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FeaturesViewModel::class.java)
        francisco = requireArguments().getParcelable(VERITRAN_CLIENT)!!

        bindViews()
        bindObservers()
    }

    private fun bindObservers() {
        viewModel.depositResult.observe(viewLifecycleOwner, {
            francisco = it.client
            featureFragmentActionObserver.showSuccesOperation(
                it
                    .client
                    .account
                    .accountBalance
                    .amount.toString())
        })

        viewModel.withdrawalResult.observe(viewLifecycleOwner, {
            francisco = it.client
            featureFragmentActionObserver.showSuccesOperation(
                it.client
                .account
                .accountBalance
                .amount.toString())
        })

        viewModel.exception.observe(viewLifecycleOwner, {
            featureFragmentActionObserver.showFeatureError(it)
        })
    }

    private fun bindViews() {
        binding.viewButtonDeposit.setOnClickListener {
            hideKeyboard(it)
            val amountToOperate = binding.viewAmountToOperateEdittext.text.toString()
            if (amountToOperate.isNotEmpty()) {
                viewModel.doDeposit(
                    francisco,
                    binding.viewAmountToOperateEdittext.text.toString()
                )
            } else{
                featureFragmentActionObserver.showFeatureError(
                    this.getString(R.string.amount_empty))
            }
        }

        binding.viewButtonWithdrawal.setOnClickListener {
            hideKeyboard(it)
            val amountToOperate = binding.viewAmountToOperateEdittext.text.toString()
            if (amountToOperate.isNotEmpty()) {
                viewModel.doWithdrawal(
                    francisco,
                    amountToOperate
                )
            } else{
                featureFragmentActionObserver.showFeatureError(
                    this.getString(R.string.amount_empty))
            }
        }

        binding.viewButtonTransfer.setOnClickListener {
            hideKeyboard(it)
            featureFragmentActionObserver
                .goToTransfer(francisco, destinyClient)
        }

        binding.viewButtonExit.setOnClickListener {
            hideKeyboard(it)
            featureFragmentActionObserver.exit()
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

interface FeatureFragmentActionObserver{
    fun goToTransfer(actualClient: ClientModel, destinyClient: ClientModel)
    fun showSuccesOperation(balance: String)
    fun showFeatureError(msg: String)
    fun exit()
}