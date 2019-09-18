package com.dr.trainup.trainingeditor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dr.trainup.trainingeditor.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class TrainingEditActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_edit)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(android.R.id.content, TrainingEditorFragment.newInstance(), "training_fragment")
        transaction.commitNow()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
