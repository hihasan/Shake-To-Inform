package com.hihasan.shaketoinform.views

import android.os.Bundle
import android.widget.Toast
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

        binding.sendBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Thank You For the Suggestions", Toast.LENGTH_SHORT).show()
//            finish()
        }
    }
}