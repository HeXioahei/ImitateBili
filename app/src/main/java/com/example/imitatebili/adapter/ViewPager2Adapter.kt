package com.example.imitatebili.adapter

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
import com.example.imitatebili.R
import com.example.imitatebili.model.Blogger

/**
 *      @bloggerList   ：up主 列表
 *      @activity      ：调用这个适配器的Activity
 */
class ViewPager2Adapter(private var bloggerList: MutableList<Blogger>,
                        private val activity: Activity) :
    RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>(){

    /*
        创建一个内部类，用来缓存 viewPager2 的数据信息
        传入 View 参数，为 viewPager2 的最外层布局
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bloggerInfo : TextView = view.findViewById(R.id.bloggerInfo)
        val bloggerDynaImage : ImageView = view.findViewById(R.id.bloggerDynaImage)
    }

    /*
        先创建一个布局，将 blogger_dynamic 布局赋给它
        再创建 ViewHolder 的实例对象，将 view 布局传进去
        返回这个实例
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view2 = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.blogger_dynamic, parent, false)
        /*
            parent 传入一个布局容器
            从上下文中获得这个 viewPager2 的布局管理，然后传入 blogger_dynamic 将其加载出来
         */
        val viewHolder2 = ViewHolder(view2)
        return viewHolder2
    }

    /*
        绑定，每出现在页面中的子项，都显示其信息
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bloggerDynamic = bloggerList[position]
        holder.bloggerDynaImage.setImageResource(bloggerDynamic.dynamicImage)
        holder.bloggerInfo.text = activity.resources.getString(bloggerDynamic.dynamicInfo)
    }

    override fun getItemCount() = bloggerList.size   // 获取子项数，为列表的元素个数
}