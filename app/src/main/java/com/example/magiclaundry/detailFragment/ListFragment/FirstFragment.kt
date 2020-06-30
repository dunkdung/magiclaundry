package com.example.magiclaundry.detailFragment.ListFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.magiclaundry.R
import com.example.magiclaundry.Utils.FirebaseUtils
import com.example.magiclaundry.detailFragment.ContentListModel
import com.example.magiclaundry.detailFragment.FirstFragAdapter
import com.example.magiclaundry.detailFragment.MarketInfo.MarketInfoActivity
import kotlinx.android.synthetic.main.fragment_first.view.*


class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_first,container,false)

        val list_array = arrayListOf<ContentListModel>(
            ContentListModel(
                R.drawable.list_image,
                "청춘세탁소",
                1,
                "d"
            ),
            ContentListModel(
                R.drawable.list2_image,
                "대우세탁소",
                1,
                "d"
            ),
            ContentListModel(
                R.drawable.list3_image,
                "타임세탁소",
                1,
                "d"
            ),
            ContentListModel(
                R.drawable.list4_image,
                "너구리세탁소",
                1,
                "d"
            ),
            ContentListModel(
                R.drawable.list5_image,
                "현대세탁소",
                1,
                "d"
            )



        )

        val list_adapter =
            FirstFragAdapter(
                requireContext(),
                list_array
            )
        view.listview_first_fragment.adapter = list_adapter

        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->

                if (documentSnapshot.exists() == true){
                    //Data 필드가 있을 때

                } else {
                    //Data 필드 없을 때

                    val loundry_shop = hashMapOf(
                        "청춘세탁소" to "",
                        "대우세탁소" to "",
                        "타임세탁소" to "",
                        "너구리세탁소" to "",
                        "현대세탁소" to ""

                    )

                    FirebaseUtils.db
                        .collection("zzim")
                        .document(FirebaseUtils.getUid())
                        .set(loundry_shop)
                        .addOnSuccessListener {  }
                        .addOnFailureListener {  }

                }

            }
            .addOnFailureListener {  }





        view.listview_first_fragment.setOnItemClickListener{AdapterView, View, i, l->
            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
            intent.putExtra("title", list_array.get(i).title)
            startActivity(intent)

        }

        return view
    }

}