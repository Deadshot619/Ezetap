package com.example.ezetap.ui.main

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.ezetap.R
import com.example.ezetap.databinding.ActivityMainBinding
import com.example.ezetap.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val mViewModel: MainViewModel by viewModels()
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onBinding() {
        setUpNavigationAndActionBar()
    }

    private fun setUpNavigationAndActionBar() {
        setSupportActionBar(mViewBinding.toolbar)
        mNavController = this.findNavController(R.id.nav_host_fragment)
        mAppBarConfiguration = AppBarConfiguration(mNavController.graph)

        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration)

        mNavController.addOnDestinationChangedListener { controller, destination, arguments ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_up_button) //Set up button as <
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
                || super.onSupportNavigateUp()
    }
}