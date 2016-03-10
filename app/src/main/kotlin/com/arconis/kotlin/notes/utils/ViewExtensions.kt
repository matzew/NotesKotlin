package com.arconis.kotlin.notes.utils

import android.app.Activity
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by lex on 03.09.15.
 */
fun EditText.value(): String {
    return this.text.toString()
}

fun EditText.hasNoErrors(errorTextId: Int, predicate: (String) -> Boolean): Boolean {
    val text = this.value()
    var hasError = false
    this.error = if (predicate(text))
        null
    else {
        hasError = true
        resources.getString(errorTextId)
    }
    return !hasError
}

fun Activity.toast(id: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, id, duration).show()
}

fun Activity.click(id: Int, action: (View) -> Unit) {
    findViewById(id).setOnClickListener { action(it) }
}

class ParcelableExtra<P : Parcelable>(private val name: String) : ReadOnlyProperty<Activity, P> {
    override fun getValue(thisRef: Activity, property: KProperty<*>): P {
        return thisRef.intent.getParcelableExtra(name)
    }
}

class ParcelableArgument<P : Parcelable>(private val name: String) : ReadOnlyProperty<Fragment, P> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): P {
        return thisRef.arguments.getParcelable<P>(name)
    }
}

class ActivityView<V : View>(private val id: Int) : ReadOnlyProperty<Activity, V> {
    override fun getValue(thisRef: Activity, property: KProperty<*>): V {
        return thisRef.findViewById(id) as V
    }
}

class FragmentView<V : View>(private val id: Int) : ReadOnlyProperty<Fragment, V> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        return thisRef.getView().findViewById(id) as V
    }
}
