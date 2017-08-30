package com.topcoder.innovate;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.topcoder.innovate.model.Listems;
import com.topcoder.innovate.model.Speaker;
import com.topcoder.innovate.util.DataRetriever;
import com.topcoder.innovate.util.new_DataRetriever;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpeakerListActivity extends AppCompatActivity {
    List<Speaker> speakerArrayList = new ArrayList<Speaker>();
    //DataRetriever myDataRetriever = new DataRetriever();

    int n = 19;
    ArrayList ids = new ArrayList();//存储图片的id
    List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_list);
        //接收HomeActivity传递的value数据，即文件的内容
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String str_1=bundle.getString("value");//接收HomeActivity传递的value数据，即文件的内容

        new_DataRetriever myDataRetriever = new new_DataRetriever();//进行数据转换
        final List<Speaker> speakerArrayList = myDataRetriever.retrieveAllSpeakers(str_1);

        //对转换后的数据作进一步处理，设定listview中列表项的内容
        Listems test=new Listems(this,n,ids,listems);
        listems=test.GetListems(speakerArrayList);

        //用SimpleAdapter设置列表项的内容
        SimpleAdapter simplead = new SimpleAdapter(this, listems,
                R.layout.activity_listview, new String[]{"name", "title", "picture"},
                new int[]{R.id.name, R.id.title, R.id.picture});
        ListView list_view = (ListView)this.findViewById(R.id.listView);
        list_view.setAdapter(simplead);
        //对SpeakerListActivity的列表项进行响应
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //添加需要响应的操作
                Intent i_2 = new Intent();
                i_2.setClass(SpeakerListActivity.this, Speaker_details.class);
                //将数据传递给Speaker_details
                Bundle b = new Bundle();
                b.putString("name", speakerArrayList.get(position).getName());
                b.putString("title", speakerArrayList.get(position).getTitle());
                b.putString("details", speakerArrayList.get(position).getDetails());
                b.putInt("image_id", (int) ids.get(position));
                i_2.putExtras(b);
                startActivity(i_2);
                //MyTask mTask = new MyTask(this);
                //mTask.execute();
            }
        });
    }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.info:
                    Intent i = new Intent(this, WebViewActivity.class);
                    startActivity(i);
                    break;
                case R.id.home:
                    Intent i_2 = new Intent(this, HomeActivity.class);
                    startActivity(i_2);
            }
        }
    }

        //若有网络请求时需要进行异步操作，不能在主线程中进行
        class MyTask extends AsyncTask<Void, List<Speaker>, List<Speaker>> {
        //onPreExecute方法用于在执行后台任务前做一些UI操作
        private Activity context;
        @Override
        protected void onPreExecute() {
        }

        public MyTask(Activity context)
        {
            this.context=context;
        }
        @Override
        protected List<Speaker> doInBackground(Void... params) {
            List<Speaker> speakerArrayList = new ArrayList<Speaker>();
            DataRetriever myDataRetriever = new DataRetriever();

            speakerArrayList = myDataRetriever.retrieveAllSpeakers(context);
            return speakerArrayList;
        }

        @Override
        protected void onPostExecute(final List<Speaker> speakerArrayList) {
            super.onPostExecute(speakerArrayList);
            int n = 19;
            final ArrayList ids = new ArrayList();
            List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
            SpeakerListActivity temp=new SpeakerListActivity();
            //对转换后的数据作进一步处理，设定listview中列表项的内容
            Listems test=new Listems(context,n,ids,listems);
            listems=test.GetListems(speakerArrayList);
            //用SimpleAdapter设置列表项的内容
            SimpleAdapter simplead = new SimpleAdapter(context, listems,
                    R.layout.activity_listview, new String[]{"name", "title", "picture"},
                    new int[]{R.id.name, R.id.title, R.id.picture});
            ListView list_view = (ListView)context.findViewById(R.id.listView);
            list_view.setAdapter(simplead);

            //对SpeakerListActivity的列表项进行响应
            list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                List<Speaker> newSpeakerList=speakerArrayList;
                ArrayList new_ids=ids;

                @Override

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //添加需要响应的操作
                    Intent i_2 = new Intent();
                    i_2.setClass(context, Speaker_details.class);
                    //将数据传递给Speaker_details
                    Bundle b = new Bundle();
                    b.putString("name", newSpeakerList.get(position).getName());
                    b.putString("title", newSpeakerList.get(position).getTitle());
                    b.putString("details", newSpeakerList.get(position).getDetails());
                    b.putInt("image_id", (int) new_ids.get(position));
                    i_2.putExtras(b);
                    context.startActivity(i_2);
                }
            });
        }

    }
