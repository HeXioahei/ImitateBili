package com.example.imitatebili

/**
 *      desc     ： 详细信息页面
 *      author   ： hexiaohei
 *      time     ： 2023/12/21
 */
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.imitatebili.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding2 : ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding2.root)
        val data2: Blogger = intent.getParcelableExtra("clickedBlogger", Blogger::class.java) as Blogger

        val button = binding2.unfollowButton

        binding2.imageDetail.setImageResource(data2.headImage)
        binding2.nameDetail.text = resources.getString(data2.name)
        binding2.detailFan.text = resources.getString(data2.detailFan)
        binding2.infoDetail.text = resources.getString(data2.detailInfo)

        button.setOnClickListener {
            Toast.makeText(this, "取关成功", Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.putExtra("delete", data2)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}