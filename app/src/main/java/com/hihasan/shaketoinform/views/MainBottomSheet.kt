package com.hihasan.shaketoinform.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

import com.hihasan.shaketoinform.R
import com.hihasan.shaketoinform.databinding.BottomsheetReportBinding
import com.hihasan.shaketoinform.utils.BaseBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainBottomSheet : BaseBottomSheetDialog() {
    @Inject
    lateinit var mainRepository: MainRepository

    private lateinit var binding : BottomsheetReportBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.BottomSheetDialogStyle)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = BottomsheetReportBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository))[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dialog?.setOnShowListener {
//            val dialog = it as BottomSheetDialog
//            val bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet)
//            bottomSheet?.let { sheet ->
//                dialog.behavior.peekHeight = sheet.height
//                sheet.parent.parent.requestLayout()
//            }
//        }
    }
}