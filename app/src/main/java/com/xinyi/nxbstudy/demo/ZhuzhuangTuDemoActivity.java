package com.xinyi.nxbstudy.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.xinyi.nxbstudy.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ZhuzhuangTuDemoActivity extends AppCompatActivity {

    private ZhuZhuangTu zzt1;
    private ZhuZhuangTu zzt2;
    private ZhuZhuangTu zzt3;
    private ZhuZhuangTu zzt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_zhuzhuang_tu_demo);
        zzt1 = (ZhuZhuangTu) findViewById(R.id.zzt1);
        zzt2 = (ZhuZhuangTu) findViewById(R.id.zzt2);
        zzt3 = (ZhuZhuangTu) findViewById(R.id.zzt3);
        zzt4 = (ZhuZhuangTu) findViewById(R.id.zzt4);
        final ArrayList<HashMap<String,String>> list = new ArrayList<>();
        HashMap<String,String> map1 = new HashMap<>();
        map1.put("name","初中");
        map1.put("value", "100");
        map1.put("unit", "元");
        list.add(map1);
        HashMap<String,String> map2 = new HashMap<>();
        map2.put("name","高中");
        map2.put("value", "300");
        map2.put("unit", "元");
        list.add(map2);
        HashMap<String,String> map3 = new HashMap<>();
        map3.put("name","大学");
        map3.put("value", "500");
        map3.put("unit", "元");
        list.add(map3);
        HashMap<String,String> map4 = new HashMap<>();
        map4.put("name","研究生");
        map4.put("value", "1000");
        map4.put("unit", "元");
        list.add(map4);
        HashMap<String,String> map5 = new HashMap<>();
        map5.put("name","博士");
        map5.put("value", "2000");
        map5.put("unit", "元");
        list.add(map5);
        HashMap<String,String> map6 = new HashMap<>();
        map6.put("name","硕导");
        map6.put("value", "3000");
        map6.put("unit", "元");
        list.add(map6);
        HashMap<String,String> map7 = new HashMap<>();
        map7.put("name","博导");
        map7.put("value", "4000");
        map7.put("unit", "元");
        list.add(map7);
        HashMap<String,String> map8 = new HashMap<>();
        map8.put("name","boss");
        map8.put("value", "6000");
        map8.put("unit", "元");
        list.add(map8);
        zzt1.setDataMap(list);
        zzt1.setAnimationDurtion(3000);
        zzt1.setTitle("学历与日薪");
        zzt1.setOnZhuZhuangTuClickListener(new OnZhuZhuangTuClickListener() {
            @Override
            public void onZhuZhuangTuClick(int position, HashMap<String, Float> locationMap) {
                Toast.makeText(ZhuzhuangTuDemoActivity.this, "第" + position + "个:" + list.get(position).get("name") + ":" + list.get(position).get("value") + list.get(position).get("unit"),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDrawFinished() {
                Toast.makeText(ZhuzhuangTuDemoActivity.this,"zzt1...绘制完成",
                        Toast.LENGTH_SHORT).show();
            }
        });
        zzt4.setDataMap(list);
        zzt4.setAnimationDurtion(18000);
        zzt4.setTitle("学历与日薪");
        zzt4.setOnZhuZhuangTuClickListener(new OnZhuZhuangTuClickListener() {
            @Override
            public void onZhuZhuangTuClick(int position, HashMap<String, Float> locationMap) {
                Toast.makeText(ZhuzhuangTuDemoActivity.this, "第" + position + "个:" + list.get(position).get("name") + ":" + list.get(position).get("value") + list.get(position).get("unit"),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDrawFinished() {
                Toast.makeText(ZhuzhuangTuDemoActivity.this,"zzt4...绘制完成",
                        Toast.LENGTH_SHORT).show();
            }
        });
        final ArrayList<HashMap<String,String>> list2 = new ArrayList<>();
        HashMap<String,String> map11 = new HashMap<>();
        map11.put("name", "动漫");
        map11.put("value", "1000");
        map11.put("unit", "部");
        list2.add(map11);
        HashMap<String,String> map12 = new HashMap<>();
        map12.put("name", "电视剧");
        map12.put("value", "200");
        map12.put("unit", "部");
        list2.add(map12);
        HashMap<String,String> map13 = new HashMap<>();
        map13.put("name", "电影");
        map13.put("value", "500");
        map13.put("unit", "部");
        list2.add(map13);
        HashMap<String,String> map14 = new HashMap<>();
        map14.put("name", "综艺");
        map14.put("value", "1500");
        map14.put("unit", "部");
        list2.add(map14);
        zzt2.setDataMap(list2);
        zzt2.setAnimationDurtion(8000);
        zzt2.setTitle("视频的崛起");
        zzt2.setOnZhuZhuangTuClickListener(new OnZhuZhuangTuClickListener() {
            @Override
            public void onZhuZhuangTuClick(int position, HashMap<String, Float> locationMap) {
                Toast.makeText(ZhuzhuangTuDemoActivity.this, "第" + position + "个:" + list2.get(position).get("name") + ":" + list2.get(position).get("value") + list2.get(position).get("unit"),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDrawFinished() {
                Toast.makeText(ZhuzhuangTuDemoActivity.this, "zzt2...绘制完成",
                        Toast.LENGTH_SHORT).show();
            }
        });
        zzt3.setDataMap(list2);
        zzt3.setAnimationDurtion(13000);
        zzt3.setTitle("视频的崛起");
        zzt3.setOnZhuZhuangTuClickListener(new OnZhuZhuangTuClickListener() {
            @Override
            public void onZhuZhuangTuClick(int position, HashMap<String, Float> locationMap) {
                Toast.makeText(ZhuzhuangTuDemoActivity.this, "第" + position + "个:" + list2.get(position).get("name") + ":" + list2.get(position).get("value") + list2.get(position).get("unit"),
                        Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar.make(zzt3,"VideoView方式查看视频",Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(ZhuzhuangTuDemoActivity.this, VideoActivity.class);
//                        startActivity(intent);
                    }
                });
                snackbar.setActionTextColor(Color.MAGENTA);
                snackbar.show();
            }
            @Override
            public void onDrawFinished() {
                Toast.makeText(ZhuzhuangTuDemoActivity.this, "zzt3...绘制完成",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
