package com.simplyandroid.myapplication.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.simplyandroid.myapplication.R
import com.simplyandroid.myapplication.database.AppDatabase

class MainFragment : Fragment() {

    private final val TAG = "room_app"

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var mView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.main_fragment, container, false)



        mView?.findViewById<Button>(R.id.get_btn)?.setOnClickListener {
            viewModel.getAllMessages(AppDatabase(activity!!.applicationContext).messageDao())

        }

        mView?.findViewById<Button>(R.id.save_btn)?.setOnClickListener {
            viewModel.saveLargeDataSet(AppDatabase(activity!!.applicationContext).messageDao())

        }

        return mView!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)






        viewModel.messages.observe(viewLifecycleOwner, Observer {


            repeat(it.size)
            { index ->
                Log.d(TAG, "message = " + it[index])
            }

        })

    }

}
