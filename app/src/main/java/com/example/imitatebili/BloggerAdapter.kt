package com.example.imitatebili

/**
 *      desc     ： recyclerView的适配器类
 *      author   ： hexiaohei
 *      time     ： 2023/12/21
 */
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 *      @bloggerList   ：up主 列表
 *      @viewPager2    ：动态信息的页面布局
 *      @activity      ：调用这个适配器的Activity
 */
class BloggerAdapter(private var bloggerList: MutableList<Blogger>,
                     private var viewPager2: ViewPager2,
                     private val activity: Activity) :
    RecyclerView.Adapter<BloggerAdapter.ViewHolder>() {     // 继承于RecyclerView.Adapter<BloggerAdapter.ViewHolder>()这个类

    // 自定义一个内部类，用来缓存每个博主的信息
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {      // 传入一个View参数，其通常就是RecyclerView子项的最外层布局
        val bloggerName: TextView = view.findViewById(R.id.bloggerName)
        val bloggerHeadImage: ImageView = view.findViewById(R.id.bloggerHeadImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.blogger_item, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.bloggerHeadImage.setOnClickListener {
            viewPager2.currentItem = viewHolder.bindingAdapterPosition   // viewPager2 的当前页随 recyclerView 的当前项变化
        }

        viewHolder.bloggerHeadImage.setOnLongClickListener {
            val data = bloggerList[viewHolder.bindingAdapterPosition]
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("clickedBlogger", data)
            activity.startActivityForResult(intent, 1)
            false
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogger = bloggerList[position]
        holder.bloggerHeadImage.setImageResource(blogger.headImage)
        holder.bloggerName.text = activity.resources.getString(blogger.name)
    }

    override fun getItemCount() = bloggerList.size     // 让 recyclerView 的项数等于博主列表的元素个数
}