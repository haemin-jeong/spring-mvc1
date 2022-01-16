package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
과거 버전의 스프링 컨트롤러를 사용해보며 스프링 MVC 동작 원리 파악하기2 - HttpRequestHandler 인터페이스 구현
1. 클라이언트가 localhost:8080/springmvc/request-handler 접속
2. DispatchServlet 에서 요청을 받는다.
3. HandlerMapping(핸들러 매핑)의 BeanNameUrlHandlerMapping(스프링 빈이름으로 핸드러를 찾는다)에서 해당 URL 을 처리할 수 있는 핸들러 MyHttpRequestHandler 를 찾아 반환한다.
3. HttpRequestHandler 인터페이스를 실행할 수 있는 HandlerAdapter(핸들러 어댑터)를 찾아서 실행한다. -> 여기서는 HttpRequestHandlerAdapter
 */
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler { //서블릿과 가장 유사한 형태의 핸들러
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
