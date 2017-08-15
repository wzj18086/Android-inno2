package com.topcoder.innovate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HomeActivity extends AppCompatActivity {
    private String value="";
    private String value_store="";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        setContentView(R.layout.activity_home);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("正在加载中，请稍后......");
        //    设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //    设置ProgressDialog样式为圆圈的形式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(97);
        if(value.length()==0) {
            MyTask my_task = new MyTask(this);
            my_task.execute();
        }
    }

    @Override
    public void finish() {
        /**
         * 记住不要执行此句 super.finish(); 因为这是父类已经实现了改方法
         * 设置该activity永不过期，即不执行onDestroy()
         */
        moveTaskToBack(true);
    }

    class MyTask extends AsyncTask<Void, Integer, String> {
        //onPreExecute方法用于在执行后台任务前做一些UI操作
        private Activity context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
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
                int file_length = inputStream.available();
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
                    //    得到当前图片下载的进度
                    int progress = ((int)((total_length/(float)file_length) * 100));
                    //    时刻将当前进度更新给onProgressUpdate方法
                    publishProgress(progress);
                    Thread.sleep(5);
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
            case R.id.info:
                Intent i = new Intent(this, WebViewActivity.class);
                startActivity(i);
                break;
            case R.id.speakers:

                Intent i_1 = new Intent(this, SpeakerListActivity.class);
                Bundle b = new Bundle();
                b.putString("value", value);
                i_1.putExtras(b);
                startActivity(i_1);
                break;
            case R.id.map:
                Intent i_2 = new Intent(this, MapActivity.class);
                startActivity(i_2);

        }
    }
    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;

        inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
