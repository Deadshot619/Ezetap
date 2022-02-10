package com.example.ezetap.ui.formdata

import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezetap.data.repository.Repository
import com.example.ezetap.databinding.FragmentFormBinding
import com.example.ezetap.model.State
import com.example.ezetap.model.domain.UiData
import com.example.ezetap.ui.base.BaseFragment

class FormDataFragment : BaseFragment<FormDataViewModel, FragmentFormBinding>() {

    override val mViewModel: FormDataViewModel by viewModels()
    override fun getViewBinding() = FragmentFormBinding.inflate(layoutInflater)

    private lateinit var mRvMain : RecyclerView
    private lateinit var mAdapter: FormDataAdapter

    private val args by navArgs<FormDataFragmentArgs>()
    private lateinit var mIvImage: AppCompatImageView

    override fun onBinding() {
        initViews()
        setUpRecyclerView()
    }

    private fun initViews() {
        mViewBinding.run {
            lifecycleOwner = viewLifecycleOwner
            mRvMain = rvMain
            mIvImage = ivImage
            initProgressBar(layoutProgress)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = FormDataAdapter()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        mRvMain.apply {
            layoutManager = linearLayoutManager
            adapter = mAdapter
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        mAdapter.submitList(args.data.toList())
    }
}
