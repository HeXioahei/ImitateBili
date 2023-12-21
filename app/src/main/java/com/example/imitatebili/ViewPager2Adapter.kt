package com.example.imitatebili

/**
 *      desc     ： viewPager2的适配器类
 *      author   ： hexiaohei
 *      time     ： 2023/12/21
 */
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *      @bloggerList   ：up主 列表
 *      @activity      ：调用这个适配器的Activity
 */
class ViewPager2Adapter(private var bloggerList: MutableList<Blogger>,
                        private val activity: Activity) :
    RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bloggerInfo : TextView = view.findViewById(R.id.bloggerInfo)
        val bloggerDynaImage : ImageView = view.findViewById(R.id.bloggerDynaImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewPager2Adapter.ViewHolder {
        val view2 = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.blogger_dynamic, parent, false)
        val viewHolder2 = ViewHolder(view2)
        return viewHolder2
    }

    override fun onBindViewHolder(holder: ViewPager2Adapter.ViewHolder, position: Int) {
        val bloggerDynamic = bloggerList[position]
        holder.bloggerDynaImage.setImageResource(bloggerDynamic.dynamicImage)
        holder.bloggerInfo.text = activity.resources.getString(bloggerDynamic.dynamicInfo)
    }

    override fun getItemCount() = bloggerList.size
}