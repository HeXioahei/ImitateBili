package com.example.imitatebili.adapter

/**
 *      desc     ： recyclerView的适配器类
 *      author   ： hexiaohei
 *      time     ： 2023/12/21
 */

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.imitatebili.R
import com.example.imitatebili.activity.DetailActivity
import com.example.imitatebili.activity.MainActivity
import com.example.imitatebili.model.Blogger

/**
 *      @bloggerList   ：up主 列表
 *      @viewPager2    ：动态信息的页面布局
 *      @activity      ：调用这个适配器的Activity
 */
class BloggerAdapter(private var bloggerList: MutableList<Blogger>,     // 传入这些参数，到时候都会用到
                     private var viewPager2: ViewPager2,
                     private val activity: MainActivity) :
    RecyclerView.Adapter<BloggerAdapter.ViewHolder>() {     // 继承于RecyclerView.Adapter<BloggerAdapter.ViewHolder>()这个类，
    // 用了泛型，这个泛型是自己定义的一个内部类

    // 自定义一个内部类，用来缓存每个博主的信息
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {      // 传入一个 View 参数，其通常就是 RecyclerView 子项的最外层布局
        val bloggerName: TextView = view.findViewById(R.id.bloggerName)       // 其实可以将 viewBinding 和 Adapter 结合，不用再 findViewById
        val bloggerHeadImage: ImageView = view.findViewById(R.id.bloggerHeadImage)
    }

    /*
        用于创建 ViewHolder 实例，
        在这个方法中将 blogger_item 布局加载进来，
        然后创建一个 ViewHolder 实例，把加载的布局传入构造函数中，
        设置一个点击事件和长按事件
        最后将 ViewHolder 的实例返回
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.blogger_item, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.bloggerHeadImage.setOnClickListener {      // 点击事件
            viewPager2.currentItem = viewHolder.bindingAdapterPosition   // viewPager2 的当前页随 recyclerView 的当前项变化
        }

        viewHolder.bloggerHeadImage.setOnLongClickListener {    // 长按事件
            val data = bloggerList[viewHolder.bindingAdapterPosition]    // getBindingAdapterPosition()
            val intent = Intent(activity, DetailActivity::class.java)    // intent传递信息
            intent.putExtra("clickedBlogger", data)
            activity.startActivityForResult(intent, 1)   // 第二个参数是请求码，用于在之后回调中判断数据的来源
            false
        }

        return viewHolder
    }

    /*  用于对 RecyclerView 子项的数据进行赋值，会在每个子项滚动到屏幕内的时候执行，
        通过 position 来获得当前的 Blogger 实例，
        然后将数据设置到 ViewHolder 的 bloggerHeadImage 和 bloggerName 中。
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogger = bloggerList[position]
        holder.bloggerHeadImage.setImageResource(blogger.headImage)
        holder.bloggerName.text = activity.resources.getString(blogger.name)
    }

    override fun getItemCount() = bloggerList.size     // 让 recyclerView 的项数等于博主列表的元素个数
}