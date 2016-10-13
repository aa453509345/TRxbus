package benmu.com.trxbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rxbus.TRxBus;
import rxbus.event.BaseEvent;

public class Main2Activity extends AppCompatActivity {

    private TextView tv;
    private Observable<BaseEvent> observable;
    StringBuffer sb=new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("2");
        tv=(TextView) findViewById(R.id.tv);
        observable= TRxBus.Bus().register("test");
        observable.subscribe(new Subscriber<BaseEvent>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseEvent event) {
                sb.append(event.content);
                tv.setText(sb);
            }
        });
    }


    public void click(View v){
        Intent intent=new Intent(this,Main3Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(observable!=null){
            TRxBus.Bus().unregister("test",observable);
        }
    }
}
