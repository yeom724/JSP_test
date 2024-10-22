package filter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class LogFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("BookMarket LogFilter 초기화...");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("LogFilter 실행중...");
		System.out.println("접속한 클라이언트 IP : " + req.getRemoteAddr());
		
		long start = System.currentTimeMillis();
		System.out.println(start);
		
		System.out.println("접근한 URL 경로 : " + getURLPath(req));
		System.out.println("요청 처리 소요 시간 : " + getCurrentTime());
		fc.doFilter(req, resp);
		
		long end = System.currentTimeMillis();
		System.out.println("요청 처리 종료 시각 : " + getCurrentTime());
		System.out.println("요청 처리 소요 시간 : " + (end-start) + "ms");
		System.out.println("====================================");
		
	}

	@Override
	public void destroy() {
		System.out.println("LogFilter 삭제중...");
	}


	private String getURLPath(ServletRequest request) {
		
		HttpServletRequest req;
		
		String currenPath = "";
		String queryString = "";
		
		if(request instanceof HttpServletRequest) {
			
			req = (HttpServletRequest)request;
			currenPath = req.getRequestURI();
			queryString = req.getQueryString();
			
			
			queryString = queryString == null ? "" : "?"+queryString;
			
		}
		
		System.out.println("getURLPath 함수 실행 : " + currenPath + queryString);
		return currenPath + queryString;
	}
	
	private String getCurrentTime() {
		
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		System.out.println("getCurrentTime 함수 실행 : " + formatter.format(calendar.getTime()));
		
		return formatter.format(calendar.getTime());
		
	}


}
