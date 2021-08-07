package com.eguerini.veritrancodeexercise.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.eguerini.veritrancodeexercise.R
import com.eguerini.veritrancodeexercise.databinding.TransferFragmentLayoutBinding
import com.eguerini.veritrancodeexercise.model.entities.ClientModel
import com.eguerini.veritrancodeexercise.ui.viewmodel.FeaturesViewModel
import com.eguerini.veritrancodeexercise.ui.viewmodel.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

const val DESTINY_CLIENT = "DESTINY_CLIENT"

class TransferFragment : DaggerFragment() {

    lateinit var binding: TransferFragmentLayoutBinding

    private lateinit var viewModel: FeaturesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var transferFragmentActionObserver: TransferFragmentActionObserver

    lateinit var emanuel: ClientModel
    lateinit var francisco: ClientModel

    companion object {
        fun getInstance(bundle: Bundle): TransferFragment {
            val fragment = TransferFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (requireActivity() is TransferFragmentActionObserver) {
            transferFragmentActionObserver = requireActivity() as TransferFragmentActionObserver
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TransferFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FeaturesViewModel::class.java)
        francisco = requireArguments().getParcelable(VERITRAN_CLIENT)!!
        emanuel = requireArguments().getParcelable(DESTINY_CLIENT)!!

        bindViews()
        bindObservers()
    }

    private fun bindViews() {
        val actualBalance = francisco.account.accountBalance.amount.toString()
        binding.viewAccountBalanceValue.text = String
            .format(this.getString(R.string.actual_balance), actualBalance)

        binding.viewButtonDoTransfer.setOnClickListener {
            hideKeyboard(it)
            onButtonTransferClicked()
        }

        binding.viewButtonBack.setOnClickListener {
            hideKeyboard(it)
            transferFragmentActionObserver.goBack()
        }
    }

    private fun bindObservers() {
        viewModel.transferResult.observe(viewLifecycleOwner, {
            francisco.account.accountBalance = it.newOriginAccountBalance
            binding.viewAccountBalanceValue.text = String
                .format(this.getString(R.string.actual_balance),
                    francisco.account.accountBalance.amount.toString())
            transferFragmentActionObserver.showSuccesTransfer()
        })

        viewModel.exception.observe(viewLifecycleOwner, {
            transferFragmentActionObserver.showFeatureError(it)
        })
    }

    private fun onButtonTransferClicked() {
        val addressee = binding.viewDestinyEdittext.text.toString()
        val amountToOperate = binding.viewAmountToTransferEdittext.text.toString()
        when {
            addressee.isEmpty() -> transferFragmentActionObserver.showFeatureError(
                this.getString(R.string.addressee_empty)
            )
            amountToOperate.isEmpty() -> transferFragmentActionObserver.showFeatureError(
                this.getString(R.string.amount_to_transfer_empty)
            )
            else ->
                viewModel.doTransfer(
                    amountToOperate,
                    binding.viewDestinyEdittext.text.toString(),
                    francisco,
                    emanuel
                )
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

interface TransferFragmentActionObserver {
    fun showSuccesTransfer()
    fun showFeatureError(msg: String)
    fun goBack()
}