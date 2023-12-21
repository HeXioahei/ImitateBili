package com.example.imitatebili

/**
 *      desc     ： 创建并初始化 up主 类的一个列表
 *      author   ： hexiaohei
 *      time     ： 2023/12/21
 */
class BloggerSender {
    fun onCreateList(): MutableList<Blogger> {
        val ao = Blogger(R.string.ao_name, R.drawable.ao_pic, R.string.ao_dynamicInfo, R.drawable.ao_dynamic_pic, R.string.ao_detailFan, R.string.ao_detailInfo)
        val yu = Blogger(R.string.yu_name, R.drawable.yu_pic, R.string.yu_dynamicInfo, R.drawable.yu_dynamic_pic, R.string.yu_detailFan, R.string.yu_detailInfo)
        val xie = Blogger(R.string.xie_name, R.drawable.xie_pic, R.string.xie_dynamicInfo, R.drawable.xie_dynamic_pic, R.string.xie_detailFan, R.string.xie_detailInfo)
        val kuang = Blogger(R.string.kuang_name, R.drawable.kuang_pic, R.string.kuang_dynamicInfo, R.drawable.kuang_dynamic_pic, R.string.kuang_detailFan, R.string.kuang_detailInfo)
        val suan = Blogger(R.string.suan_name, R.drawable.suan_pic, R.string.suan_dynamicInfo, R.drawable.suan_dynamic_pic, R.string.suan_detailFan, R.string.suan_detailInfo)
        val bi = Blogger(R.string.bi_name, R.drawable.bi_pic, R.string.bi_dynamicInfo, R.drawable.bi_dynamic_pic, R.string.bi_detailFan, R.string.bi_detailInfo)
        val reng = Blogger(R.string.reng_name, R.drawable.reng_pic, R.string.reng_dynamicInfo, R.drawable.reng_dynamic_pic, R.string.reng_detailFan, R.string.reng_detailInfo)
        val zhan = Blogger(R.string.zhan_name, R.drawable.zhan_pic, R.string.zhan_dynamicInfo, R.drawable.zhan_dynamic_pic, R.string.zhan_detailFan, R.string.zhan_detailInfo)
        val you = Blogger(R.string.you_name, R.drawable.you_pic, R.string.you_dynamicInfo, R.drawable.you_dynamic_pic, R.string.you_detailFan, R.string.you_detailInfo)

        return mutableListOf(ao, yu, xie, kuang, suan, bi, reng, zhan, you)
    }
}