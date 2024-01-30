package com.example.imitatebili.activity

/**
 *      desc     ： 详细信息页面
 *      author   ： hexiaohei
 *      time     ： 2023/12/21
 */
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.imitatebili.model.Blogger
import com.example.imitatebili.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var data2 : Blogger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding2 : ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        // 获取传递过来的博主信息
        data2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("clickedBlogger", Blogger::class.java) as Blogger
        }
        else {
            intent.getSerializableExtra("clickedBlogger") as Blogger
        }
        //data2 = intent.getSerializableExtra("clickedBlogger") as Blogger
        val button = binding2.unfollowButton

        // 呈现信息
        binding2.imageDetail.setImageResource(data2.headImage)
        binding2.nameDetail.text = resources.getString(data2.name)
        binding2.detailFan.text = resources.getString(data2.detailFan)
        binding2.infoDetail.text = resources.getString(data2.detailInfo)

        // 设置取关点击事件
        button.setOnClickListener {
            Toast.makeText(this, "取关成功", Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.putExtra("delete", data2)  // 将信息放入intent
            setResult(RESULT_OK, intent)    // 返回。结果码
            finish()
        }
    }
}