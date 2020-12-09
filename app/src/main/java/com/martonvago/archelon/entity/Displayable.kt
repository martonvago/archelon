package com.martonvago.archelon.entity

import android.os.Parcelable

interface Displayable: Parcelable {
    val displayName: String
}

// Used to attach the toDisplayable extension function to enums
// which are used as options in select input elements
interface DisplayableCompanion<T : Enum<T>>