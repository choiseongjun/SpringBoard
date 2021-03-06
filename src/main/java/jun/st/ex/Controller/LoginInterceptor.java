package jun.st.ex.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	//메인 액션이 실행되기 전
		@Override
		public boolean preHandle(HttpServletRequest request
				, HttpServletResponse response, Object handler) 
						throws Exception {
			   response.setContentType("text/html; charset=UTF-8");

			 
			 
			//세션 객체 생성
			HttpSession session=request.getSession();
			
		String path = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("!@#$%^&*()!@#$%^&*(!@#$%^&*"+path);	
		//세션이 없으면(로그인되지 않은 상태)
			if(session.getAttribute("userid") == null) {
				
				session.setAttribute("savePage", path);//로그인 후  가야할 요청 페이지 저장.
				
				//login 페이지로 이동 
				response.sendRedirect(request.getContextPath()
						+"/member/Login.do?message=nologin");
					
				return false; //메인 액션으로 가지 않음
			}else {
				
				return true; //메인 액션으로 이동
			}
		}
		//메인 액션이 실행된 후
		@Override
		public void postHandle(HttpServletRequest request
				, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			
			
			super.postHandle(request, response, handler, modelAndView);
			  
		}
}
 