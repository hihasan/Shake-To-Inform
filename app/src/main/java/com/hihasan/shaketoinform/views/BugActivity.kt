package com.hihasan.shaketoinform.views

import android.os.Bundle
import com.hihasan.shaketoinform.databinding.ActivityReportBinding
import com.hihasan.shaketoinform.utils.BaseActivity

class BugActivity : BaseActivity() {
    private lateinit var binding : ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initListeners()
    }

    private fun initListeners() {
        binding.backIv.setOnClickListener {
            finish()
        }
    }
}