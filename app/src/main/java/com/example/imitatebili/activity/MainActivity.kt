package com.example.imitatebili.activity

/**
 *      author : hexiaohei
 *      time   : 2023/12/21
 *      desc   : 主页面
 */
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.imitatebili.model.Blogger
import com.example.imitatebili.adapter.BloggerAdapter
import com.example.imitatebili.model.BloggerSender
import com.example.imitatebili.adapter.ViewPager2Adapter
import com.example.imitatebili.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 先进行定义，再在方法中进行初始化
    private var bloggerList = BloggerSender().onCreateList()
    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerViewAdapter : BloggerAdapter
    private lateinit var viewPager2Adapter : ViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)       // 呈现布局，root代表在此布局文件下的所有布局

        // 先创建适配器实例
        recyclerViewAdapter = BloggerAdapter(bloggerList, binding.viewPager2, this)
        viewPager2Adapter = ViewPager2Adapter(bloggerList, this)

        // 再调用 setAdapter()
        binding.recyclerView.adapter = recyclerViewAdapter
        binding.viewPager2.adapter = viewPager2Adapter

        // 创建一个布局管理器，用于指定 RecyclerView 的布局方式，确定 RecyclerView 中每个项的位置和大小
        val layoutManager1 = LinearLayoutManager(this)    // 指明其为线性布局
        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL     // 水平方向

        // 用 setLayoutManager() 将这个布局管理器实例赋给 recyclerView
        binding.recyclerView.layoutManager = layoutManager1

        // recyclerView的当前项随着viewPager2的当前页变化
        /* registerOnPageChangeCallback() 需要一个 OnPageChangeCallback()类的实例来作为参数，
           而这里就传了 ViewPager2 的 OnPageChangeCallback() 抽象类实例进去
           并实现 onPageSelected(position: Int) 方法，用此方法来得到 viewPager2 的当前页位置信息
           再在此方法中调用 recyclerView 的 scrollToPosition()方法，来改变 recyclerView 的当前页
        */
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.recyclerView.scrollToPosition(position)
            }
        })
    }

    /**
     *     desc : 获取由 DetailActivity 返回的数据，据此数据来更新列表和页面
           requestCode 是将 MainActivity 中的信息传给 DetailActivity 时传递的请求码
           resultCode 是将信息反馈回来的验证数码
           前者可以用来检验信息是否成功传到 Detail，后者用来检测时 Detail 中的哪个点击事件触发了信息的反馈。
    * */
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Log.d("data", data.toString())
            val deleteBlogger: Blogger = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                data?.getParcelableExtra("delete", Blogger::class.java) as Blogger   // 反编码，由字节流变回类对象
            } else {
                data?.getSerializableExtra("delete") as Blogger   // 反编码，由字节流变回类对象
            }
            // val deleteBlogger: Blogger  = data?.getSerializableExtra("delete") as Blogger
            // val deleteBlogger : Blogger = data?.getParcelableExtra("delete", Blogger::class.java) as Blogger
            var index: Int = -1
            for (a in 0..<bloggerList.size) {
                if (deleteBlogger.name == bloggerList[a].name) {
                    bloggerList.removeAt(a)    // 删除位置 a 的元素
                    index = a    // 获取这个元素的位置，方便下面 notify
                    break
                }
            }
            recyclerViewAdapter.notifyItemRemoved(index)   // notify
            viewPager2Adapter.notifyItemRemoved(index)
        }
    }
}