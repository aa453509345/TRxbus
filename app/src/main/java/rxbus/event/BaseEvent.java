package rxbus.event;

/**
 * Created by Carry on 16/10/13.
 */

public class BaseEvent {


    public BaseEvent(String id,String content){
        this.id=id;
        this.content=content;
    }
    public String id;
    public Object content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
