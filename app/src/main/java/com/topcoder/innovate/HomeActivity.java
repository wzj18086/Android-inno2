package com.topcoder.innovate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class HomeActivity extends AppCompatActivity {
    private String value="";
    private String value_store="";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        setContentView(R.layout.activity_home);

        //设置进度条相关属性
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("正在加载中，请稍后......");
        //    设置setCancelable(false); 表示我们不能取消这个弹出框，等加载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //    设置ProgressDialog样式为矩形的形式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(97);
        if(value.length()==0) {
            MyTask my_task = new MyTask(this);
            my_task.execute();
        }
    }

    @Override
    public void finish() {
        moveTaskToBack(true);
    }
    //使用AsyncTask来完成异步操作
    class MyTask extends AsyncTask<Void, Integer, String> {
        private Activity context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();//显示进度条
        }

        public MyTask(Activity context) {
            this.context = context;
        }
        private InputStream inputStream=null;
        @Override
        protected String doInBackground(Void... params) {
            try
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                inputStream = getResources().getAssets().open("speakers.txt");
                int file_length = inputStream.available();//获取资源的大小
                //    每次读取后累加的长度
                long total_length = 0;
                int length = 0;
                //    每次读取1024个字节
                byte[] data = new byte[1024];
                while(-1 != (length = inputStream.read(data)))
                {
                    //    每读一次，就将total_length累加起来
                    total_length += length;
                    //    边读边写到ByteArrayOutputStream当中
                    byteArrayOutputStream.write(data, 0, length);
                    //    得到当前资源加载的进度
                    int progress = ((int)((total_length/(float)file_length) * 100));
                    //    时刻将当前进度更新给onProgressUpdate方法
                    publishProgress(progress);
                    Thread.sleep(5);//每执行一次读取操作之后，睡眠5us来展示进度条效果
                }
                value=byteArrayOutputStream.toString();
                inputStream.close();
                byteArrayOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

            return value;
        }
        @Override
        protected void onProgressUpdate(Integer... progresses) {
            super.onProgressUpdate(progresses);
            progressDialog.setProgress(progresses[0]);
        }
        @Override
        protected void onPostExecute(String value) {
            super.onPostExecute(value);
            progressDialog.dismiss();
        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            //点击相关图标后启动WebViewActivity，打开网页
            case R.id.info:
                Intent i = new Intent(this, WebViewActivity.class);
                startActivity(i);
                break;
            case R.id.speakers:
            //点击相关图标后启动SpeakerListActivity
                Intent i_1 = new Intent(this, SpeakerListActivity.class);
                Bundle b = new Bundle();
                b.putString("value", value);
                i_1.putExtras(b);
                startActivity(i_1);
                break;
            case R.id.map:
            //点击相关图标后启动MapActivity
                Intent i_2 = new Intent(this, MapActivity.class);
                startActivity(i_2);

        }
    }

}
