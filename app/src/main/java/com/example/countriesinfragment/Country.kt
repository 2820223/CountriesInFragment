package com.example.countriesinfragment

data class Country(
    var name: String,
    var population: String,
    var area: String,
    var img: Int,
    var about: String,
    var status:Boolean = false
) : java.io.Serializable
