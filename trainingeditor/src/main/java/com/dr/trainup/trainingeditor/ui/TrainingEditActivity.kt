package com.dr.trainup.trainingeditor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dr.trainup.trainingeditor.R

class TrainingEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_edit)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(android.R.id.content, TrainingEditorFragment.newInstance(), "training_fragment")
        transaction.commitNow()
    }
}
