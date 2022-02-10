package com.example.ezetap.ui.form

import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezetap.data.repository.Repository
import com.example.ezetap.databinding.FragmentFormBinding
import com.example.ezetap.model.State
import com.example.ezetap.model.domain.UiData
import com.example.ezetap.ui.base.BaseFragment
import com.example.ezetap.utils.bindImage
import com.example.ezetap.utils.extensions.showToast

class FormFragment : BaseFragment<FormViewModel, FragmentFormBinding>() {

    private val repository = Repository()

    override val mViewModel: FormViewModel by viewModels{ FormViewModelFactory(repository) }
    override fun getViewBinding() = FragmentFormBinding.inflate(layoutInflater)

    private lateinit var mRvMain : RecyclerView
    private lateinit var mAdapter: FormAdapter

    private lateinit var mIvImage: AppCompatImageView

    override fun onBinding() {
        initViews()
        setUpRecyclerView()
        setUpObservers()
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
        mAdapter = FormAdapter(object : IFormButtonClickListener {
            override fun onButtonClick() {
                /*mAdapter.currentList.map {
                    if (it.userInput.trim().isNotEmpty()) showToast(it.userInput)
                }*/
                goToNextPage(mAdapter.currentList)
            }
        })
        val linearLayoutManager = LinearLayoutManager(requireContext())
        mRvMain.apply {
            layoutManager = linearLayoutManager
            adapter = mAdapter
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setUpObservers() {
        mViewModel.uiData.observe(viewLifecycleOwner) {
            it.peekContent().let {
                when (it) {
                    is State.Loading -> {
                        showProgressBar()
                    }
                    is State.Success -> {
                        bindImage(mIvImage, it.data.logoUrl)
                        mAdapter.submitList(it.data.uiData)
                        showProgressBar(false)
                    }
                    is State.Failed -> {
                        showToast(it.message)
                        showProgressBar(false)
                    }
                }
            }
        }
    }

    private fun goToNextPage(data: List<UiData>){
        findNavController().navigate(FormFragmentDirections.actionFeedFragmentToFormDataFragment(data.toTypedArray()))
    }
}
