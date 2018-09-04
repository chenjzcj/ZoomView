package com.felix.zoomview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.felix.zoomview.zoomview.ZoomTextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view);
        textView.setText(readTextFromAssets(this));
        new ZoomTextView(textView, 0.8f);
    }

    /**
     * 从assets中读取txt文本
     *
     * @param context 上下文
     * @return 读取的文本信息
     */
    public static String readTextFromAssets(Context context) {
        InputStream is = context.getClass().getResourceAsStream("/assets/text.txt");
        try {
            int index = is.available();
            byte[] data = new byte[index];
            is.read(data);
            return new String(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
