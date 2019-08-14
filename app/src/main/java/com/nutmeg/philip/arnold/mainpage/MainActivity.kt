package com.nutmeg.philip.arnold.mainpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nutmeg.philip.arnold.R
import com.nutmeg.philip.arnold.retrofit.RetrofitFactory
import com.nutmeg.philip.arnold.retrofit.RetrofitRxFactory
import com.nutmeg.philip.arnold.retrofit.data.PostData
import com.nutmeg.philip.arnold.retrofit.data.User
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserResponseInterface, DataResponseInterface {
    private var mCompositeDisposable: CompositeDisposable? = null
    val users: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = RecyclerAdapter(ArrayList())

        rx.setOnClickListener { getUsersFromRx() }
        coroutine.setOnClickListener { getUsersFromCoroutine() }

        getUsersFromRx()
    }
    fun getUsersFromRx() {
        progress_circular.show()
        mCompositeDisposable = CompositeDisposable()
        RetrofitRxFactory.getUsers(this)
    }
    fun getUsersFromCoroutine() {
        progress_circular.show()
        RetrofitFactory.getUsers(this)
    }

    fun getDataFromRx() {
        ReturnData(ArrayList())
        progress_circular.show()
        mCompositeDisposable = CompositeDisposable()
        RetrofitRxFactory.getPosts(this)
    }
    fun getDataFromCoroutine() {
        ReturnData(ArrayList())
        progress_circular.show()
        RetrofitFactory.getPosts(this)
    }

    override fun ReturnUsers(newUsers: ArrayList<User>, fromRx: Boolean) {
        users.clear()
        users.addAll(newUsers)
        if (fromRx) {
            getDataFromRx()
        } else {
            getDataFromCoroutine()
        }
    }
    override fun ReturnData(data: ArrayList<PostData>) {
        for (i in 0..data.size - 1) {
            val entry = data.get(i)
            for (user in users) {
                if (user.id == entry.userId) {
                    entry.user = user
                    break
                }
            }
            data.set(i, entry)
        }

        progress_circular.hide()
        (recycler.adapter as RecyclerAdapter).UpdateData(data)
    }

    override fun ReturnError(error: Throwable) {
        progress_circular.hide()
        Toast.makeText(this, "error: " + error.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
