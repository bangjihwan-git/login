package hello.login.web.filter;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("log filter doFilter");
		HttpServletRequest httpRequest= (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		
		String uuid = UUID.randomUUID().toString();
		
		try {
			log.info("REQUEST [{}][{}]", uuid, requestURI);
			chain.doFilter(request, response); //필수로 실행시켜야함 필터가 있으면 필터실행 없으면 servlet실행
		} catch (Exception e) {
			throw e;
		} finally {
			log.info("RESPONSE [{}][{}]", uuid, requestURI);
		}
	}

}
