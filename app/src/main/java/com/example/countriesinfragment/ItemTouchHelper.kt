package com.example.countriesinfragment

interface ItemTouchHelper {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}