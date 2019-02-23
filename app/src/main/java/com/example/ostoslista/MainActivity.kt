package com.example.ostoslista

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter
import android.widget.ListView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {

    private var ostosLista = ArrayList<String>()
    private var listView: ListView? = null
    private var listAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { showNewTaskUI() }
        listView = findViewById(R.id.list_view)
        populateListView()
    }

    fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.add_new_task_dialog_title)
        newFragment.show(supportFragmentManager, "newtask")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: String) {
        Snackbar.make(fab, "Tuotteen lisäys onnistui", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        ostosLista.add(task)
        listAdapter?.notifyDataSetChanged()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {

    }

    private fun populateListView() {
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ostosLista)
        listView?.adapter = listAdapter
    }

}
