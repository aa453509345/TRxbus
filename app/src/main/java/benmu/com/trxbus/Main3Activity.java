package benmu.com.trxbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import rxbus.TRxBus;
import rxbus.event.BaseEvent;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("3");

    }

    public void click(View v){
        TRxBus.Bus().post("test",new BaseEvent("test","WE ARE SUCCESS"));
    }
}
