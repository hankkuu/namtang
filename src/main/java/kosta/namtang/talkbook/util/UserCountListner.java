package kosta.namtang.talkbook.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserCountListner implements ApplicationListener<ApplicationStartedEvent>,HttpSessionListener {
	
	   @Override
	    public void onApplicationEvent(ApplicationStartedEvent event) {
	        System.out.println("==========================");
	        System.out.println("Application Started");
	    }
	   @Override
	    public void sessionCreated(HttpSessionEvent sessionEve) {
	        
	        // 세션이 새로 생성되면 execute() 실행한다.
	        if(sessionEve.getSession().isNew()){
	            execute(sessionEve);
	        }
	    }
	 
	    private void execute(HttpSessionEvent sessionEve) 
	    {
	    	System.out.println("===== 새로운 방문자 =====\n");
	        try {
	        	//HttpSession session = sessionEve.getSession();
	        	/*
	            session.setAttribute("totalCount", totalCount); 
	            System.out.println(totalCount);
	            */
	        } catch (Exception e) {
	            System.out.println("===== 방문자 카운터 오류 =====\n");
	            //e.printStackTrace();
	        }
	    }
	    @Override
	    public void sessionDestroyed(HttpSessionEvent arg0) {}

	   
}

