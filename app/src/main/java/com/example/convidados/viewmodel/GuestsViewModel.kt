package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.service.constants.GuestsConstants
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int){

        if(filter == GuestsConstants.FILTER.EMPTY) {
            mGuestList.value = mGuestRepository.getAll()
        } else if (filter == GuestsConstants.FILTER.PRESENT) {
            mGuestList.value = mGuestRepository.getPresent()
        } else {
            mGuestList.value = mGuestRepository.getAbsent()
        }
    }

    fun delete(id: Int){
        val guest = mGuestRepository.get(id)
        mGuestRepository.delete(guest)
    }
}