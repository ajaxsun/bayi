package com.node.bayi.Entity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 孙伟
 * Date: 2018/10/13
 * Email: 1580440730@qq.com
 * Describe:
 */
@SuppressLint("ParcelCreator")
public class MapInfoEntity implements Parcelable {
    String name;
    String address;
    String hour;

    public MapInfoEntity() {
    }

    public MapInfoEntity(String name, String address, String hour) {
        this.name = name;
        this.address = address;
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(hour);
    }

    public static final Parcelable.Creator<MapInfoEntity> creater = new ClassLoaderCreator<MapInfoEntity>() {
        @Override
        public MapInfoEntity createFromParcel(Parcel source, ClassLoader loader) {
            MapInfoEntity mapInfoEntity = new MapInfoEntity();
            mapInfoEntity.setName(source.readString());
            mapInfoEntity.setAddress(source.readString());
            mapInfoEntity.setHour(source.readString());
            return mapInfoEntity;
        }

        @Override
        public MapInfoEntity createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public MapInfoEntity[] newArray(int size) {
            return new MapInfoEntity[0];
        }
    };
}
