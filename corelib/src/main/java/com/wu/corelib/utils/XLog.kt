package com.wu.corelib.utils

import android.util.Log
import com.wu.corelib.BuildConfig

class XLog {

    companion object {
        private const val TAG = "wu"

        fun i(tag: String, o: Any) {
            if (BuildConfig.DEBUG) {
                Log.i(TAG, "【$tag】$o")
            }
        }

        fun i(o: Any) {
            if (BuildConfig.DEBUG) {
                Log.i(TAG, "【${o}】")
            }
        }


        fun d(tag: String, o: Any) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "【$tag】$o")
            }
        }

        fun d(o: Any) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "【${o}】")
            }
        }


        fun e(tag: String, o: Any) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "【$tag】$o")
            }
        }

        fun e(o: Any) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "【${o}】")
            }
        }


        fun w(tag: String, o: Any) {
            if (BuildConfig.DEBUG) {
                Log.w(TAG, "【$tag】$o")
            }
        }

        fun w(o: Any) {
            if (BuildConfig.DEBUG) {
                Log.w(TAG, "【${o}】")
            }
        }
    }
}