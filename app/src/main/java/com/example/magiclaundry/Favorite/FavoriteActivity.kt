package com.example.magiclaundry.Favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.magiclaundry.R
import com.example.magiclaundry.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    val array_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)


        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener {documentSnapshot ->

                if(documentSnapshot.get("청춘세탁소") == true){
                    array_list.add("청춘세탁소")
                }

                if(documentSnapshot.get("대우세탁소") == true){
                    array_list.add("대우세탁소")
                }

                if(documentSnapshot.get("타임세탁소") == true){
                    array_list.add("타임세탁소")
                }

                if(documentSnapshot.get("너구리세탁소") == true){
                    array_list.add("너구리세탁소")
                }

                if(documentSnapshot.get("현대세탁소") == true){
                    array_list.add("현대세탁소")
                }



                val favorite_Adapter = Favoriteadapter(this, array_list)
                zzim_listview.adapter = favorite_Adapter



            }
            .addOnFailureListener {  }


    }
}