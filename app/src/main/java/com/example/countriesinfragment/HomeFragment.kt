package com.example.countriesinfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.countriesinfragment.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var list: MutableList<Country>
    private var isFav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        list = mutableListOf()
        loadData()
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rv.setHasFixedSize(true)
        var adapter = CountryAdapter(list, object : CountryAdapter.MyCountry {
            override fun onItemClick(country: Country) {
                val bundle = bundleOf("country" to country)
                findNavController().navigate(R.id.action_homeFragment_to_moreInfoFragment, bundle)
            }

        }, requireActivity())
        binding.rv.adapter = adapter

        binding.fav.setOnClickListener {
            if (!isFav) {
                binding.fav.setImageResource(R.drawable.fav)
                isFav = true
                binding.fav.tag = 1
                var filter = list.filter { it.status }
                adapter = CountryAdapter(
                    filter as MutableList<Country>,
                    object : CountryAdapter.MyCountry {
                        override fun onItemClick(country: Country) {
                            val bundle = bundleOf("country" to country)
                            findNavController().navigate(
                                R.id.action_homeFragment_to_moreInfoFragment,
                                bundle
                            )
                        }

                    }, requireActivity()
                )
                binding.rv.adapter = adapter
            } else {
                binding.fav.setImageResource(R.drawable.un_fuv)
                isFav = false
                binding.fav.tag = 0
                adapter = CountryAdapter(list, object : CountryAdapter.MyCountry {
                    override fun onItemClick(country: Country) {
                        val bundle = bundleOf("country" to country)
                        findNavController().navigate(
                            R.id.action_homeFragment_to_moreInfoFragment,
                            bundle
                        )
                    }

                }, requireActivity())
                binding.rv.adapter = adapter
            }
        }


        val touch = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.onItemDismiss(viewHolder.adapterPosition)
            }


        }
        val itemTouchHelper = ItemTouchHelper(touch)
        itemTouchHelper.attachToRecyclerView(binding.rv)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rv)
        binding.rv.adapter = adapter
        return binding.root
    }

    private fun loadData() {
        list.add(
            Country(
                "Palestine",
                "38,93 mln",
                "652,860 km²",
                R.drawable.pakes,
                "Palestine  is a country with a beautiful view, consisting of 12 regions"
            )
        )
        list.add(
            Country(
                "Filippino",
                "2,87 mln",
                "27,400 km²",
                R.drawable.filipino,
                "Filippino  is a country with a beautiful view, consisting of 12 regions"
            )
        )

        list.add(
            Country(
                "Austria",
                "43,85 mln",
                "2,38 mln km²",
                R.drawable.austria,
                "Austria  is a country with a beautiful view, consisting of 12 regions"
            )
        )
        list.add(
            Country(
                "Uzbekistan",
                "77 265",
                "470 km²",
                R.drawable.uzb,
                "Uzbekistan  is a country with a beautiful view, consisting of 12 regions"
            )
        )
        list.add(
            Country(
                "Russia",
                "32,86 mln",
                "1,24 mln km²",
                R.drawable.russia,
                "Russia  is a country with a beautiful view, consisting of 12 regions"
            )
        )
        list.add(
            Country(
                "Ukrain",
                "45,19 mln",
                "2,73 mln km²",
                R.drawable.ukrain,
                "Ukrain  is a country with a beautiful view, consisting of 12 regions"
            )
        )
        list.add(
            Country(
                "Bolgaria",
                "2,96 mln",
                "28,470 km²",
                R.drawable.vbulgar,
                "Bolgaria  is a country with a beautiful view, consisting of 12 regions"
            )
        )
        list.add(
            Country(
                "United States",
                "25,49 mln",
                "7,68 mln km²",
                R.drawable.usj,
                "United States  is a country with a beautiful view, consisting of 12 regions"
            )
        )


        list.add(
            Country(
                "China",
                "1,43 bln",
                "9,38 mln km²",
                R.drawable.china,
                "China  is a country with a beautiful view, consisting of 12 regions"
            )
        )



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}