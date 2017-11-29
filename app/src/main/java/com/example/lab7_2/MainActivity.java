package com.example.lab7_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {
    ImageView iw;
    float startX1 = 1;
    float startY1 = 1;
    float startX2 = 1;
    float startY2 = 1;
    float f = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startX1 = 1;
        startY1 = 1;
        startX2 = 1;
        startY2 = 1;
        iw = new ImageView(this);
        iw.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iw.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
        iw.setOnTouchListener(this);
        setContentView(iw);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // событие
        int actionMask = event.getActionMasked();
        // индекс касания
        int pointerIndex = event.getActionIndex();
        // число касаний
        int pointerCount = event.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: // первое касание
                startX1 = (int) event.getX(0);
                startY1 = (int) event.getY(0);
            case MotionEvent.ACTION_POINTER_DOWN: // последующие касания
                if (pointerCount == 2) {
                    startX2 = (int) event.getX(1);
                    startY2 = (int) event.getY(1);
                }
                break;

            case MotionEvent.ACTION_UP: // прерывание последнего касания
                //inTouch = false;
                //  sb.setLength(0);
            case MotionEvent.ACTION_POINTER_UP: // прерывания касаний
                // upPI = pointerIndex;
                break;

            case MotionEvent.ACTION_MOVE: // движение

                if (pointerCount == 2) {
                    if (event.getY(0) < event.getY(1)) {
                        if (startX1 < event.getX(0) && startY1 > event.getY(0) && startX2 > event.getX(1) && startY2 < event.getY(1)) {
                            f += 0.1f;
                            iw.setScaleX(f);
                            iw.setScaleY(f);
                            startX1 = event.getX(0);
                            startY1 = event.getY(0);
                            startX2 = event.getX(1);
                            startY2 = event.getY(1);
                        } else if (startX1 > event.getX(0) && startY1 < event.getY(0) && startX2 < event.getX(1) && startY2 > event.getY(1)) {
                            f -= 0.1f;
                            iw.setScaleX(f);
                            iw.setScaleY(f);
                            startX1 = event.getX(0);
                            startY1 = event.getY(0);
                            startX2 = event.getX(1);
                            startY2 = event.getY(1);
                        }
                    } else {
                        if (event.getY(0) > event.getY(1)) {
                            if (startX2 < event.getX(1) && startY2 > event.getY(1) && startX1 > event.getX(0) && startY1 < event.getY(0)) {
                                f += 0.1f;
                                iw.setScaleX(f);
                                iw.setScaleY(f);
                                startX1 = event.getX(0);
                                startY1 = event.getY(0);
                                startX2 = event.getX(1);
                                startY2 = event.getY(1);
                            } else if (startX2 > event.getX(1) && startY2 < event.getY(1) && startX1 < event.getX(0) && startY1 > event.getY(0)) {
                                f -= 0.1f;
                                iw.setScaleX(f);
                                iw.setScaleY(f);
                                startX1 = event.getX(0);
                                startY1 = event.getY(0);
                                startX2 = event.getX(1);
                                startY2 = event.getY(1);
                            }
                        }
                    }
                }
                break;
        }
        return true;
    }
}
