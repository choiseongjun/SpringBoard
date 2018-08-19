package jun.st.ex.Controller;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogAdvice {
	private static final Logger logger
	=LoggerFactory.getLogger(LogAdvice.class);
	
	@Around(
			"execution(* jun.st.ex.Controller..*Controller.*(..))"
			+" or execution(* jun.st.ex.Service..*Impl.*(..))"
			+" or execution(* jun.st.ex.Persistence..dao.*Impl.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint)
	throws Throwable{
		long start=System.currentTimeMillis();
		Object result=joinPoint.proceed();
		String type=
					joinPoint.getSignature().getDeclaringTypeName();
		String name="";
		if(type.indexOf("Controller")>-1) {
			name="controller \t:";
		}else if(type.indexOf("Service")>-1) {
			name="ServiceImpl \t:";
		}else if(type.indexOf("DAO")>-1) {
			name="DaoImpl \t:";
		}
		logger.info(name+type+"."+joinPoint.getSignature().getName()+"()");
		logger.info(Arrays.toString(joinPoint.getArgs()));
		long end=System.currentTimeMillis();
		long time=end-start;
		logger.info("실행시간:"+time);
		return result;
	}
}
