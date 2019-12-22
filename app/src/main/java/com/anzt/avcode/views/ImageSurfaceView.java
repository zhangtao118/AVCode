package com.anzt.avcode.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.anzt.avcode.R;

public class ImageSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Bitmap bitmap;

    public ImageSurfaceView(Context context) {
        super(context);
        init();
    }

    public ImageSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                drawImage();
            }
        }).start();
    }

    private void init(){
        this.setZOrderOnTop(true);
        //得到控制器
        surfaceHolder=getHolder();
        //对surfaceview进行操作
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        //创建画笔
        paint=new Paint();
        //创建图片对象
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.test);
    }

    private void drawImage() {
        //锁定画布
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bitmap,new Matrix(),paint);
        //解锁画布
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
