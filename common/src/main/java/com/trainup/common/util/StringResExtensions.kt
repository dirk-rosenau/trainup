package com.trainup.common.util


fun Int.toStrResource(vararg args: Any?) = StringResource(this, args.toList())
