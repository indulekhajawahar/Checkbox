package com.example.checkbox


import android.graphics.Color
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var txtProgress: TextView
    lateinit var cross_image:ImageView
    lateinit var checkbtn:Button
    lateinit var crossbtn:Button
    private var progressBar: ProgressBar? = null
    private var progressBar2: ProgressBar? = null
    private var pStatus = 0
    private val handler: Handler = Handler()
lateinit var image:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       txtProgress = findViewById(R.id.textView);
       cross_image=findViewById(R.id.cross_image)
       checkbtn=findViewById(R.id.check_btn)
       crossbtn=findViewById(R.id.cross_btn)
        progressBar = findViewById(R.id.progressBar);
      progressBar2 = findViewById(R.id.progressBar2);
        image=findViewById(R.id.imageView)
        //image_cross=findViewById(R.id.imageView_cross)
        val animZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        val animZoomInP = AnimationUtils.loadAnimation(this, R.anim.zoom_in_progress)
       checkbtn?.setOnClickListener(){
        Thread {
            while (pStatus <= 100) {
                handler.post {
                    progressBar?.setProgress(pStatus)
                    txtProgress?.setText("$pStatus %")
                    while (pStatus==100){
                        (image.getDrawable() as Animatable).start()
                    }
                }
                try {
                    Thread.sleep(25)

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                pStatus++
            }
           /*while(pStatus==100){
               progressBar?.getProgressDrawable()?.setColorFilter(
                   Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
           }*/
        }.start()

        image.startAnimation(animZoomIn)
        progressBar?.startAnimation(animZoomInP)
        }
       crossbtn.setOnClickListener(){
           progressBar?.setVisibility(View.VISIBLE)
           Thread {
               while (pStatus <= 100) {
                   handler.post {
                       progressBar?.setProgress(pStatus)
                       txtProgress?.setText("$pStatus %")
                       while (pStatus==100){
                           progressBar?.setVisibility(View.GONE)
                          progressBar2?.setVisibility(View.VISIBLE)
                          cross_image.setVisibility(View.VISIBLE)
                       }
                   }
                   try {
                       Thread.sleep(25)

                   } catch (e: InterruptedException) {
                       e.printStackTrace()
                   }
                   pStatus++
               }
           }.start()
       }
    }
}