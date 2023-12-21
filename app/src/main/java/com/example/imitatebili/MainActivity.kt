package com.example.imitatebili

/**
 *      author : hexiaohei
 *      time   : 2023/12/21
 *      desc   : 主页面
 */
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.imitatebili.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var bloggerList = BloggerSender().onCreateList()
    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerViewAdapter : BloggerAdapter
    private lateinit var viewPager2Adapter : ViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewAdapter = BloggerAdapter(bloggerList, binding.viewPager2, this)
        viewPager2Adapter = ViewPager2Adapter(bloggerList, this)

        binding.recyclerView.adapter = recyclerViewAdapter
        binding.viewPager2.adapter = viewPager2Adapter

        // 创建一个布局管理器，用于确定RecyclerView中每个项的位置和大小
        val layoutManager1 = LinearLayoutManager(this)
        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerView.layoutManager = layoutManager1

        // recyclerView的当前项随着viewPager2的当前页变化
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.recyclerView.scrollToPosition(position)
            }
        })
    }

    /**
     *     desc : 获取由DetailActivity返回的数据，据此数据来更新列表和页面
     */
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Log.d("data", data.toString())
            val deleteBlogger: Blogger = data?.getParcelableExtra("delete", Blogger::class.java) as Blogger
            var index: Int = -1
            for (a in 0..<bloggerList.size) {
                if (deleteBlogger.name == bloggerList[a].name) {
                    bloggerList.removeAt(a)
                    index = a
                    break
                }
            }
            recyclerViewAdapter.notifyItemRemoved(index)
            viewPager2Adapter.notifyItemRemoved(index)
        }
    }
}