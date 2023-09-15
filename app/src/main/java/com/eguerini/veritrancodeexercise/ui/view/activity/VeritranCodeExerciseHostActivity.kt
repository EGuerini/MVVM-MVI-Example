package com.eguerini.veritrancodeexercise.ui.view.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.eguerini.veritrancodeexercise.R
import com.eguerini.veritrancodeexercise.databinding.HostActivityBinding
import com.eguerini.veritrancodeexercise.model.entities.ClientModel
import com.eguerini.veritrancodeexercise.ui.view.fragment.*
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

const val MAIN_TAG = "main_tag"

class VeritranCodeExerciseHostActivity: DaggerAppCompatActivity(),
    LoginFragmentActionsObserver,
    FeatureFragmentActionObserver,
    TransferFragmentActionObserver {

    private lateinit var binding: HostActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = HostActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        launchLogin()
    }

    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.container_host)){
            is TransferFragment -> supportFragmentManager.popBackStack()
            is LoginFragment -> finish()
        }
    }

    //region FeatureFragmentActionsObserver
    override fun goToTransfer(actualClient: ClientModel, destinyClient: ClientModel) {
        val bundle = Bundle()
        bundle.putParcelable(DESTINY_CLIENT, destinyClient)
        bundle.putParcelable(VERITRAN_CLIENT, actualClient)
        val transferFragment = TransferFragment.getInstance(bundle)
        replaceFragment(transferFragment, true, MAIN_TAG)
    }

    override fun showSuccesOperation(balance: String) {
        Snackbar
            .make(binding.root, "NUEVO SALDO DE CUENTA: USD $balance", Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat
                .getColor(this, android.R.color.holo_green_light))
            .show()
    }

    override fun showFeatureError(msg: String) {
        Snackbar
            .make(binding.root, msg, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_red_light))
            .show()
    }

    override fun exit() {
        launchLogin()
    }
    //endregion

    //region LoginFragmentActionsObserver
    override fun goToFeaturesFragments(clientModel: ClientModel) {
        val bundle = Bundle()
        bundle.putParcelable(VERITRAN_CLIENT, clientModel)
        val featuresFragment = FeaturesFragment.getInstance(bundle)
        replaceFragment(featuresFragment, true, MAIN_TAG)
    }

    override fun showLoginError(msg: String?) {
        Snackbar
            .make(binding.root, msg!!, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_red_light))
            .show()
    }
    //endregion

    //region TransferFragmentActionsObserver
    override fun showSuccesTransfer() {
        Snackbar
            .make(binding.root, "Transferencia OK", Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat
                .getColor(this, android.R.color.holo_green_light))
            .show()
    }

    override fun goBack() {
        onBackPressed()
    }
    //endregion

    private fun replaceFragment(fragment: Fragment,
                                addToBackStack: Boolean,
                                tag: String?){
        val ft = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            .replace(R.id.container_host, fragment, tag)
        when(addToBackStack){
            true -> ft.addToBackStack(fragment.javaClass.simpleName)
        }
        ft.commitAllowingStateLoss()
    }

    private fun launchLogin() {
        val loginFragment = LoginFragment.getInstance()
        replaceFragment(loginFragment, false, MAIN_TAG)
    }
}