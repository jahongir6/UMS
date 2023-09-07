package com.example.ums.fragment

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.ums.R
import com.example.ums.adapters.RvAdapter
import com.example.ums.adapters.RvClicked
import com.example.ums.databinding.FragmentSecondBinding
import com.example.ums.db.MyData
import com.example.ums.models.InfoData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment(), RvClicked {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var binding: FragmentSecondBinding
    private lateinit var adapter: RvAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        when (arguments?.getString("key")?.toInt()) {
            0 -> {
                binding.label.text = "Tariflar"
                adapter = RvAdapter(requireContext(), MyData.getData0(), 0, this)
            }
            1 -> {
                binding.label.text = "Xizmatlar"
                adapter = RvAdapter(requireContext(), MyData.getData1(), 1, this)
            }
            2 -> {
                binding.label.text = "Daqiqalar"
                adapter = RvAdapter(requireContext(), MyData.getData2(), 2, this)
            }
            3 -> {
                binding.label.text = "Internet"
                adapter = RvAdapter(requireContext(), MyData.getData3(), 3, this)
            }
            4 -> {
                binding.label.text = "SMS"
                adapter = RvAdapter(requireContext(), MyData.getData4(), 4, this)
            }
            5 -> {
                binding.label.text = "USSD Toplamlar"
                adapter = RvAdapter(requireContext(), MyData.getData5(), 5, this)
            }
        }
        binding.myRv.adapter = adapter


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun itemClicked(infoData: InfoData, keyItem: Int) {
        findNavController().navigate(
            R.id.thirdFragment, bundleOf("keyInfo" to infoData, "keyItem" to keyItem),
        )
    }
}