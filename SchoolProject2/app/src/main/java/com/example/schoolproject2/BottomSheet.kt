package com.example.schoolproject2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolproject2.databinding.BottomsheetlayoutBinding
import com.example.schoolproject2.ui.BottomSheet.BottomSheetAdapter
import com.example.schoolproject2.ui.BottomSheet.IAdapterSelection
import com.example.schoolproject2.ui.BottomSheet.IListSelector
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheetlayout.*

class BottomSheet : BottomSheetDialogFragment() {

    companion object {
        private val TAG = BottomSheet::class.simpleName
        var list: MutableList<String>? = null
        var adaptionSelection: IAdapterSelection? = null
        var customTitle: String? = null
        var customText: String? = null

        fun instance(list: MutableList<String>, customTitle: String, customText: String,  adaptionSelection: IAdapterSelection) : BottomSheet {
            Companion.list = list
            Companion.adaptionSelection = adaptionSelection
            Companion.customTitle = customTitle
            Companion.customText = customText
            return BottomSheet()
        }
    }

    private var tempInt: Int? = null
    private lateinit var binding: BottomsheetlayoutBinding
    private lateinit var adapter: BottomSheetAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottomsheetlayout, container,  false)
        binding.listview.text = customTitle!!
        adapter = BottomSheetAdapter(requireContext(), list!!, object: IListSelector {
            override fun itemSelected(index: Int) {
                if (customText!! == list!![index]) {
                    binding.customtext.visibility = View.VISIBLE
                    binding.submitbutton.visibility = View.VISIBLE
                } else {
                    adaptionSelection!!.onStringSelected(list!![index])
                   dismiss()
                }
                tempInt = index
            }
        })

        binding.submitbutton.setOnClickListener {
            if ( tempInt != null) {
                if (tempInt == 0) {
                    adaptionSelection!!.onStringSelected(binding.customtext.text.toString())
                    dismiss()
                } else {
                    adaptionSelection!!.onStringSelected(list!![tempInt!!])
                    dismiss()
                }
            }
        }

        val layoutManager = LinearLayoutManager(this.context)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = layoutManager



        return binding.root
    }



}
