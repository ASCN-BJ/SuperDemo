package jxcsuper.cxb.com.textlibra;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private TextView tv_onclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aaaaaaaaa);
        tv_onclick = (TextView) findViewById(R.id.tv_onclick);
        tv_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "这是一个类库", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
