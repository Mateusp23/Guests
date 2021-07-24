package com.example.convidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application){

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>() // MutableLiveData o valor pode ser modificado
    val saveGuest: LiveData<Boolean> = mSaveGuest // LiveData o valor não pode ser modificado


    private var mGuest = MutableLiveData<GuestModel>() // MutableLiveData o valor pode ser modificado
    val guest: LiveData<GuestModel> = mGuest

    fun save (id: Int, name: String, presence: Boolean) {
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.presence = presence
        }

        if(id == 0){
            mSaveGuest.value = mGuestRepository.save(guest)
        } else {
            mSaveGuest.value = mGuestRepository.update(guest)
        }
    }

    fun load(id: Int) {
        mGuest.value = mGuestRepository.get(id)
    }

}