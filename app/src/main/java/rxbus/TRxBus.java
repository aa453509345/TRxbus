package rxbus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import rxbus.event.BaseEvent;

/**
 * Created by Carry on 16/10/13.
 */

public class TRxBus {
    private static volatile TRxBus mBus;
    private ConcurrentHashMap<Object, List<SerializedSubject>> mSubjectMapper;


    private TRxBus() {
        mSubjectMapper = new ConcurrentHashMap();
    }

    public static TRxBus Bus() {
        TRxBus temp = mBus;
        if (temp == null) {
            synchronized (TRxBus.class) {
                temp = mBus;
                if (temp == null) {
                    temp = new TRxBus();
                    mBus = temp;
                }
            }
        }

        return temp;
    }


    public Observable<BaseEvent> register(String tag) {
        List<SerializedSubject> list = mSubjectMapper.get(tag);
        if (list == null) {
            list=new ArrayList<SerializedSubject>();
            mSubjectMapper.put(tag,list);
        }
        SerializedSubject<BaseEvent,BaseEvent> subject=new SerializedSubject<>(PublishSubject.<BaseEvent>create());
        list.add(subject);
        return subject;
    }


    public void unregister(String tag,Observable observable){
        List<SerializedSubject> list=mSubjectMapper.get(tag);
        if(list!=null&&observable!=null){
             list.remove((Subject) observable);
              if(list.size() ==0 ){
                  mSubjectMapper.remove(tag);
              }
        }
    }

    public void post(String tag,BaseEvent event){
        List<SerializedSubject> list=mSubjectMapper.get(tag);
        if(list!=null){
            for (Subject subject:list){
                 if(hasObservable(subject)){
                     subject.onNext(event);
                 }
            }
        }
    }


    public boolean hasObservable(Subject subject){
        if(subject ==null){
            return false;
        }else {
            return subject.hasObservers();
        }
    }


}
