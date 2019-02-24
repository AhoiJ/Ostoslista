package com.example.ostoslista

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.*

import kotlinx.android.synthetic.main.dialog_new_task.*
import org.w3c.dom.Text

class NewTaskDialogFragment : DialogFragment() {  // 1

    // 2
    interface NewTaskDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, task: String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    var newTaskDialogListener: NewTaskDialogListener? = null // 3

    companion object {
        fun newInstance(title: Int): NewTaskDialogFragment {

            val newTaskDialogFragment = NewTaskDialogFragment()
            val args = Bundle()
            args.putInt("dialog_title", title)
            newTaskDialogFragment.arguments = args
            return newTaskDialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog { // 5
        val title = arguments!!.getInt("dialog_title")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)

        val dialogView = activity?.layoutInflater?.inflate(R.layout.dialog_new_task, null)
        val task = dialogView?.findViewById<EditText>(R.id.task_view)



        builder.setView(dialogView)
            .setPositiveButton(R.string.save) { dialog, id ->
                newTaskDialogListener?.onDialogPositiveClick(
                    this,
                    task?.text.toString()
                );
            }
            .setNegativeButton(R.string.cancel_window) { dialog,
                                                          id ->
                newTaskDialogListener?.onDialogNegativeClick(this)
            }
        return builder.create()
    }

    override fun onAttach(activity: Activity) { // 6
        super.onAttach(activity)
        try {
            newTaskDialogListener = activity as NewTaskDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString() + " must implement NewTaskDialogListener"
            )
        }

    }
}