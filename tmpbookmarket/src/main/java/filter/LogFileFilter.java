package filter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

public class LogFileFilter implements Filter{
	
	PrintWriter writer;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("LogFileFilter 초기화...");
		
		String filename = filterConfig.getInitParameter("filename");
		
		if(filename == null) {
			throw new ServletException("로그 파일의 이름을 찾을 수 없습니다.");
		}
		
		try {
			writer = new PrintWriter(new FileWriter(filename, true), true);
		} catch(IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
	}



	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		
		System.out.println("LogFilter 실행중...");
		writer.println("접속한 클라이언트 IP : " + req.getRemoteAddr());
		
		long start = System.currentTimeMillis();
		System.out.println(start);
		
		writer.println("접근한 URL 경로 : " + getURLPath(req));
		writer.println("요청 처리 소요 시간 : " + getCurrentTime());
		fc.doFilter(req, resp);
		
		long end = System.currentTimeMillis();
		writer.println("요청 처리 종료 시각 : " + getCurrentTime());
		writer.println("요청 처리 소요 시간 : " + (end-start) + "ms");
		writer.println("====================================");
		
	}

	@Override
	public void destroy() {
		System.out.println("LogFileFilter 삭제중...");
		writer.close();
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
