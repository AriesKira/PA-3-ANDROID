package com.example.senanas.model

import android.os.Parcel
import android.os.Parcelable

data class LoginResponseDto(
    val token: String?,
    val user: UserDto?

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(UserDto::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginResponseDto> {
        override fun createFromParcel(parcel: Parcel): LoginResponseDto {
            return LoginResponseDto(parcel)
        }

        override fun newArray(size: Int): Array<LoginResponseDto?> {
            return arrayOfNulls(size)
        }
    }

}


data class UserDto(
    val firstname:String,
    val lastname:String,
    val email:String,
    val role:String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstname)
        parcel.writeString(lastname)
        parcel.writeString(email)
        parcel.writeString(role)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDto> {
        override fun createFromParcel(parcel: Parcel): UserDto {
            return UserDto(parcel)
        }

        override fun newArray(size: Int): Array<UserDto?> {
            return arrayOfNulls(size)
        }
    }
}
