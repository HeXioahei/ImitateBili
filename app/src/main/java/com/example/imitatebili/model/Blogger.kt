package com.example.imitatebili.model

/**
 *      desc   ：创建一个 up主 类，规定和存放 up主 的信息
 *      author ：hexiaohei
 *      time   ：2023/12/21
 */
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 *      @name           ：存放 名字 的位置
 *      @headImagine    ：存放 头像图片 的位置
 *      @dynamicInfo    ：存放 动态简介 的位置
 *      @dynamicImagine ：存放 动态信息 的位置
 *      @detailFan      ：存放 粉丝数 的位置
 *      @detailInfo     ：存放其他 具体信息 的位置
 *      @Parcelable     ：实现接口，为使 intent 能够传递 Blogger对象
 */

// 实现 Parcelable 接口，使 Blogger 对象在 Activity 间可以被传递，通过 Parcel 类来传递。
class Blogger (val name: Int, val headImage: Int, val dynamicInfo: Int, val dynamicImage: Int, val detailFan: Int, val detailInfo: Int)
    : Serializable
//    ,Parcelable {
//
//    // 读取
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt()
//    )
//
//    override fun describeContents(): Int {
//        TODO("Not yet implemented")
//    }
//
//    // 写入
//    override fun writeToParcel(parcel: Parcel, p1: Int) {
//        parcel.writeInt(name)
//        parcel.writeInt(headImage)
//        parcel.writeInt(dynamicInfo)
//        parcel.writeInt(detailFan)
//        parcel.writeInt(dynamicImage)
//        parcel.writeInt(detailInfo)
//    }
//
//    /*
//        在用到Parcel进行一些存储的时候，需要用到这个Parcelable 接口。
//        在 implements 这个 Parcelable 接口的时候，必须同时顶一个 static 的变量 CREATOR，类型是Parcelable.Creator。
//        kotlin 没有静态方法，是用单例类来实现的，在单例类里面写的方法相当于是静态方法
//     */
//    companion object CREATOR : Parcelable.Creator<Blogger> {
//        override fun createFromParcel(parcel: Parcel): Blogger {
//            return Blogger(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Blogger?> {
//            return arrayOfNulls(size)
//        }
//    }
//}