package com.xinyi.nxbstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niu on 2017/12/29.
 */

public class CricleFragment extends BaseFragment {

    private List<Map<String, String>> list;
    private int[] colors = new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.GRAY, Color.GREEN, Color.BLACK};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        list = new ArrayList<>();

        Map<String, String> map4 = new HashMap<>();
        map4.put("name", "D");
        map4.put("value", "200");
        list.add(map4);
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "A");
        map1.put("value", "50");
        list.add(map1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "B");
        map2.put("value", "20");
        list.add(map2);
        Map<String, String> map3 = new HashMap<>();
        map3.put("name", "C");
        map3.put("value", "100");
        list.add(map3);

        Map<String, String> map5 = new HashMap<>();
        map5.put("name", "E");
        map5.put("value", "40");
        list.add(map5);
        Map<String, String> map6 = new HashMap<>();
        map6.put("name", "F");
        map6.put("value", "120");
        list.add(map6);


        return new CircleView(getActivity(), list);
    }


    private class CircleView extends View {
        private Paint paint;
        private List<Map<String, String>> data;
        private List<Map<String, String>> arcData;
        private float[] angles;
        private float perAngle;

        private List<Float[]> lines;

        private int width;
        private int height;

        private int cenX;
        private int cenY;
        private int radius;

        private int maxAnglePosition = 0;

        public CircleView(Context context, List<Map<String, String>> data) {
            super(context);
            this.data = data;
            angles = new float[data.size()];
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            width = DensityUtils.getScreenWidth(context);
            height = width * 2 / 3;
            caculateData();
        }

        private void caculateData() {
            int tNum = 0;
            for (int i = 0; i < data.size(); i++) {
                tNum += Integer.parseInt(data.get(i).get("value"));
            }

            perAngle = (float) 360 / tNum;

            arcData = initArc();


            cenX = width / 2;
            cenY = height / 2;
            radius = (height - 200) / 2;

        }

//        private List<Float[]> initLines(){
//            List<Float[]> list=new ArrayList<>();
//            for (int i = 0; i <data.size() ; i++) {
//                Float[] floats=new Float[2];
//                floats[0]=
//            }
//        }


        private List<Map<String, String>> initArc() {
            float mAngle = 0;
            List<Map<String, String>> list = new ArrayList<>();
            float sweepAngle = 0;
            float startAngle = 180;
            for (int i = 0; i < data.size(); i++) {
                Map<String, String> map = new HashMap<>();
                map.putAll(data.get(i));
                startAngle += sweepAngle;
                map.put("startAngle", String.valueOf(startAngle));
                sweepAngle = Float.parseFloat(data.get(i).get("value")) * perAngle;
                if (sweepAngle > mAngle) {
                    mAngle = sweepAngle;
                }
                String angle = String.valueOf(sweepAngle);
                map.put("sweepAngle", angle);
                list.add(map);
                angles[i] = 0;
            }

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).get("sweepAngle").equals(String.valueOf(mAngle))) {
                    maxAnglePosition = i;
                    break;
                }
            }
            return list;
        }

        boolean run = true;

        @Override
        public void layout(int l, int t, int r, int b) {
            super.layout(l, t, r, b);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    while (run) {
                        try {
                            Thread.sleep(15);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < angles.length; i++) {

                            angles[i] += 1;
                            if (angles[i] > Float.parseFloat(arcData.get(i).get("sweepAngle"))) {
                                angles[i] = Float.parseFloat(arcData.get(i).get("sweepAngle"));

                                if (i == maxAnglePosition) {
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

            RectF rect = new RectF();
            rect.left = cenX - radius;
            rect.top = cenY - radius;
            rect.right = cenX + radius;
            rect.bottom = cenY + radius;

            RectF rect1 = new RectF();
            rect1.left = cenX - 10 - radius;
            rect1.top = cenY - 10 - radius;
            rect1.right = cenX - 10 + radius;
            rect1.bottom = cenY - 10 + radius;
            //画扇形
            for (int i = 0; i < arcData.size(); i++) {
                if (i > colors.length - 1) {
                    paint.setColor(colors[0]);
                } else {
                    paint.setColor(colors[i]);
                }

                if (i == maxAnglePosition) {
                    canvas.drawArc(rect1, Float.parseFloat(arcData.get(i).get("startAngle")),
                            angles[i], true, paint);
                } else {

                    canvas.drawArc(rect, Float.parseFloat(arcData.get(i).get("startAngle")),
                            angles[i], true, paint);
                }

            }

            //画标记


        }
    }
}
