package com.example.imitatebili

/**
 *      desc   ：创建一个 up主 类，规定和存放 up主 的信息
 *      author ：hexiaohei
 *      time   ：2023/12/21
 */
import android.os.Parcel
import android.os.Parcelable

/**
 *      @name           ：存放 名字 的位置
 *      @headImagine    ：存放 头像图片 的位置
 *      @dynamicInfo    ：存放 动态简介 的位置
 *      @dynamicImagine ：存放 动态信息 的位置
 *      @detailFan      ：存放 粉丝数 的位置
 *      @detailInfo     ：存放其他 具体信息 的位置
 *      @Parcelable     ：实现接口，为使 intent 能够传递 Blogger对象
 */
class Blogger (val name: Int, val headImage: Int, val dynamicInfo: Int, val dynamicImage: Int, val detailFan: Int, val detailInfo: Int) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(name)
        parcel.writeInt(headImage)
        parcel.writeInt(dynamicInfo)
        parcel.writeInt(dynamicImage)
        parcel.writeInt(detailFan)
        parcel.writeInt(detailInfo)
    }

    companion object CREATOR : Parcelable.Creator<Blogger> {
        override fun createFromParcel(parcel: Parcel): Blogger {
            return Blogger(parcel)
        }

        override fun newArray(size: Int): Array<Blogger?> {
            return arrayOfNulls(size)
        }
    }
}