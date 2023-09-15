package com.eguerini.veritrancodeexercise.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.eguerini.veritrancodeexercise.R
import com.eguerini.veritrancodeexercise.databinding.LoginFragmentLayoutBinding
import com.eguerini.veritrancodeexercise.di.annotation.Choose
import com.eguerini.veritrancodeexercise.model.entities.ClientModel
import com.eguerini.veritrancodeexercise.model.state.MainState
import com.eguerini.veritrancodeexercise.ui.intent.LoginIntent
import com.eguerini.veritrancodeexercise.ui.viewmodel.LoginViewModel
import com.eguerini.veritrancodeexercise.ui.viewmodel.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

const val VERITRAN_CLIENT = "VERITRAN_CLIENT"

class LoginFragment: DaggerFragment() {

    private lateinit var binding: LoginFragmentLayoutBinding
    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var loginFragmentActionsObserver: LoginFragmentActionsObserver

    @Inject @Choose("francisco")
    lateinit var francisco: ClientModel

    companion object{
        fun getInstance(): LoginFragment = LoginFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if(requireActivity() is LoginFragmentActionsObserver){
            loginFragmentActionsObserver = requireActivity() as LoginFragmentActionsObserver
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        bindViews()
        bindObservers()
    }

    private fun bindViews() {
        binding.viewButtonLogin.setOnClickListener {
            hideKeyboard(it)
            lifecycleScope.launch {
                when(val userId = binding.viewUserId.text.toString()) {
                    "" -> loginFragmentActionsObserver
                        .showLoginError(getString(R.string.write_user_id))
                    else -> viewModel.userIntent.send(LoginIntent.RequestLogin(francisco, userId))
                }
            }
        }
    }

    private fun bindObservers() {
        lifecycleScope.launch{
            viewModel.mainState.collect { mainState ->
                when(mainState){
                    is MainState.Login -> loginFragmentActionsObserver.goToFeaturesFragments(francisco)
                    is MainState.Error -> loginFragmentActionsObserver.showLoginError(mainState.error)
                    MainState.Loading -> TODO("aca se puede mostrar un progress")
                }
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

interface LoginFragmentActionsObserver{
    fun goToFeaturesFragments(clientModel: ClientModel)
    fun showLoginError(msg: String?)
}