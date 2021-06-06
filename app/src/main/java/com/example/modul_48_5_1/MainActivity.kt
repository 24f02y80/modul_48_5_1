package com.example.modul_48_5_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.*;
import io.reactivex.rxjava3.core.Observable
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // разделить положительные и отрицательные значения
        val observable = Observable.just(1,2,3,-4,5,-6)
            .groupBy { it > 0 }
            .flatMapSingle { it.toList() }
            .subscribe{
                println("!!!$it")
            }

        // выводить слова, пока их длина менее 6 символов;
        val observable2 = Observable.just("Hello", "Skill", "Factory", "!")
            .takeWhile { it.length < 6 }
            .subscribe {
                println("!!!string < 6 = $it")
            }

        // пропускать все отрицательные значения, пока не придет одно положительное;
        val observable3 = Observable.just(-2,-1,1, 2, 3, 4)
            .skipWhile { it<0 }
            .subscribe {
                println("!!!skipWhile {it<0} =  $it")
            }

        //выводить все слова, пока не придет слово stop
        val observable4 = Observable.just("start", "go","run", "stop","nothing")
            .takeUntil { it == "stop" }
            .subscribe {
                println("!!!game - $it")
            }
    }
}