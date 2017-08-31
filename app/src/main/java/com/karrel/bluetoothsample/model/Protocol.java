package com.karrel.bluetoothsample.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Rell on 2017. 8. 30..
 */

public class Protocol extends RealmObject implements Parcelable {
    public String name;
    public String hex;
    public String uuid;
    public boolean isChecksum;

    public Protocol() {

    }

    protected Protocol(Parcel in) {
        name = in.readString();
        hex = in.readString();
        uuid = in.readString();
        isChecksum = in.readByte() != 0;
    }

    public static final Creator<Protocol> CREATOR = new Creator<Protocol>() {
        @Override
        public Protocol createFromParcel(Parcel in) {
            return new Protocol(in);
        }

        @Override
        public Protocol[] newArray(int size) {
            return new Protocol[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(hex);
        parcel.writeString(uuid);
        parcel.writeByte((byte) (isChecksum ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "hex='" + hex + '\'' +
                ", isChecksum=" + isChecksum +
                ", name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                "} " + super.toString();
    }
}
