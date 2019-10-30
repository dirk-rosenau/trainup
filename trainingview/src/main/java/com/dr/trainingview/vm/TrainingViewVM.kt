package com.dr.trainingview.vm

import android.app.Application
import com.trainup.common.ObservableViewModel
import javax.inject.Inject

class TrainingViewVM @Inject constructor(
    app: Application
) : ObservableViewModel(app)