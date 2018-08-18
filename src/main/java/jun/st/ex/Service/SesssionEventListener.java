package jun.st.ex.Service;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
 
 
public class SesssionEventListener implements HttpSessionBindingListener{
 
    @Override
    public void valueBound(HttpSessionBindingEvent event){
        if (SessionLoginPreventor.findByLoginId(event.getName())){
        	SessionLoginPreventor.invalidateByLoginId(event.getName());
        }
        SessionLoginPreventor.loginUsers.put(event.getName(), event.getSession());
    }
 
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
    	SessionLoginPreventor.loginUsers.remove(event.getName(), event.getSession());
    }
}
