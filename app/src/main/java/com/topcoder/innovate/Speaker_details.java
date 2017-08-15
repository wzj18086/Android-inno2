package com.topcoder.innovate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Speaker_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_details);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        String str_1=bundle.getString("name");
        TextView name=(TextView) findViewById(R.id.name_details);
        name.setText(str_1);

        String str=bundle.getString("title");
        TextView title=(TextView)findViewById(R.id.title_details);
        title.setText(str);

        String str_2=bundle.getString("details");
        TextView details=(TextView)findViewById(R.id.detail_details);
        details.setText(str_2);

        int pic_id=bundle.getInt("image_id");
        ImageView picture=(ImageView)findViewById(R.id.pic_details);
        picture.setImageResource(pic_id);
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
            case R.id.return_button:
                this.finish();
        }
    }
}
