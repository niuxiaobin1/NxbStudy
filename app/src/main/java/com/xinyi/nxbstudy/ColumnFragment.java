package com.xinyi.nxbstudy;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niu on 2017/12/29.
 */

public class ColumnFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(String.valueOf(i), Math.round(Math.random() * 1000) + "");
        }
        return new ColumnView(getActivity(), map);
    }


    private class ColumnView extends View {

        private Paint paint;
        private Map<String, String> datas;//数据源
        private int width = 0;//绘制内容的宽高 含有100的padding
        private int height = 0;//
        private int paintWith = 3;//画笔宽度
        private float paintTextSize = 25f;
        private int columnWidth = 100;//每个柱状图的宽度
        private float perH = 0;//以数据里最大的数值为基准，每个单位的高度，也就是perh*value=绘制柱状图的高度
        private float perW = 0;//每个柱状图的间隔
        private Rect[] rects;
        private int[] delts;


        public ColumnView(Context context, Map<String, String> datas) {
            super(context);
            this.datas = datas;
            rects = new Rect[datas.size()];
            delts = new int[datas.size()];
            paint = new Paint();
            paint.setTextSize(60);
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(paintWith);
            //默认宽高比3：2
            width = DensityUtils.getScreenWidth(context);
            height = width * 2 / 3;
            calculationPoint();

        }

        long maxV = 0;
        int maxPosition = 0;

        private void calculationPoint() {

            //计算 200：左右上下各有100px的padding
            if (datas.size() == 0) {
                return;
            }

            maxV = 0;
            for (String key :
                    datas.keySet()) {
                maxV = maxV > Long.parseLong(datas.get(key)) ? maxV : Long.parseLong(datas.get(key));
            }


            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(String.valueOf(i)).equals(String.valueOf(maxV))) {
                    maxPosition = i;
                    break;
                }
            }

            perH = (float) (height - 200 - paintWith) / maxV;
            perW = (float) (width - 200 - paintWith - (columnWidth * datas.size())) / (datas.size() + 1);
            for (int i = 0; i < datas.size(); i++) {
                int left = (int) ((perW * (i + 1)) + 100 + paintWith + columnWidth * i);
                int top = height - 100 - paintWith - (int) (Integer.parseInt(datas.get(String.valueOf(i))) * perH);
                int right = left + columnWidth;
                int bottom = height - 100 - paintWith;

                rects[i] = new Rect(left, top, right, bottom);
                delts[i] = 0;
            }


        }

        private boolean run = true;

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            Log.e("nxb", "onLayout");
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.e("nxb", "onMeasure");
        }

        @Override
        public void layout(int l, int t, int r, int b) {
            super.layout(l, t, r, b);


            new Thread() {
                @Override
                public void run() {
                    super.run();

                    while (run) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < datas.size(); i++) {
                            delts[i] += 10;
                            if (delts[i] >= rects[i].bottom - rects[i].top) {
                                delts[i] = rects[i].bottom - rects[i].top;

                                if (i == maxPosition) {
                                    run = false;
                                }
                            }
                        }
                        postInvalidate();
                    }
                }
            }.start();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            float[] points = new float[]{100, 100, 100, height - 100, 100, height - 100, width - 100, height - 100};
            if (datas.size() == 0) {
                return;
            }
            if (perH == 0 || perW == 0) {
                return;
            }
            paint.setColor(Color.WHITE);
            canvas.drawLines(points, paint);
            paint.setColor(Color.GREEN);

            for (int i = 0; i < rects.length; i++) {
                Rect rect = new Rect(rects[i].left, rects[i].bottom - delts[i], rects[i].right, rects[i].bottom);
                canvas.drawRect(rect, paint);

                //TODO drawText
//                canvas.rotate(90);
//                paint.setColor(Color.BLACK);
//                paint.setTextAlign(Paint.Align.LEFT);
//                canvas.drawText("5", rects[i].left, rects[i].bottom + paintWith, paint);
//                canvas.rotate(-90);
//                paint.setColor(Color.GREEN);
            }


//            new DrawThread(canvas).start();


        }


        private class DrawThread extends Thread {

            Canvas canvas;

            public DrawThread(Canvas canvas) {
                this.canvas = canvas;
            }

            @Override
            public void run() {
                super.run();

                for (int j = 0; j < 10; j++) {
                    for (int i = 0; i < rects.length; i++) {
                        Rect rect = new Rect(rects[i].left / 10 * j, rects[i].top / 10 * j, rects[i].right, rects[i].bottom);
                        canvas.drawRect(rect, paint);

                        //TODO drawText
//                canvas.rotate(90);
//                paint.setColor(Color.BLACK);
//                paint.setTextAlign(Paint.Align.LEFT);
//                canvas.drawText("5", rects[i].left, rects[i].bottom + paintWith, paint);
//                canvas.rotate(-90);
//                paint.setColor(Color.GREEN);
                    }
                }
            }
        }
    }
}
