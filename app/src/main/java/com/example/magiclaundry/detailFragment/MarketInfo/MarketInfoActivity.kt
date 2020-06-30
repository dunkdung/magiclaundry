package com.example.magiclaundry.detailFragment.MarketInfo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import com.example.magiclaundry.R
import com.example.magiclaundry.Utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_market_info.*
import java.lang.reflect.TypeVariable

class MarketInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_info)

        lecture_text.text = intent.getStringExtra("title")
// 찜여부 확인
        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener {documentSnapshot ->

                if(documentSnapshot.get(intent.getStringExtra("title")) == true){

                    header_zzim.text= "찜 목록에 추가 되었습니다."
                    header_zzim.setTextColor(Color.BLUE)

                }
            }
            .addOnFailureListener {  }



//        val loundry_shop = hashMapOf(
//            "loundry_shop_title" to intent.getStringExtra("title")
//        )
        zzim.setOnClickListener {


            //이미 찜 되어있을 떄
            if (header_zzim.text.equals("찜 목록에 추가 되었습니다.")){


                header_zzim.text= "찜 목록에 넣기"
                header_zzim.setTextColor(Color.RED)

                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title"), "")
                    .addOnSuccessListener {
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }

            } else {
                //이미 찜 되어있지 않을 때

                header_zzim.text= "찜 목록에 추가 되었습니다."
                header_zzim.setTextColor(Color.BLUE)


                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title"), true)
                    .addOnSuccessListener {
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()

                    }

            }






        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_area, ContentFragment())
            .commit()


        figure_1.setOnClickListener{
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, ContentFragment())
                .commit()
        }
        figure_2.setOnClickListener{
                figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
                figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
                figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, InfoFragment())
                .commit()
        }
        figure_3.setOnClickListener{
                figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
                figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
                figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, ReviewFragment())
                .commit()
        }
    }
}