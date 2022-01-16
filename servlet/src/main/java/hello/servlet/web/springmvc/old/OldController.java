package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
가장 우선 순위가 높은 HandlerMapping 은 RequestMappingHandlerMapping, HandlerAdapter 는 RequestMappingHandlerAdapter 이다.
이 둘은 현재 스프링에서 가장 많이 사용하는 애노테이션(@RequestMapping) 기반의 컨트롤러를 지원하는 매핑과 어댑터이다.

과거 버전의 스프링 컨트롤러를 사용해보며 스프링 MVC 동작 원리 파악하기1 - Controller 인터페이스 구현
1. 클라이언트가 localhost:8080/springmvc/old-controller 접속
2. DispatchServlet 에서 요청을 받는다.
3. HandlerMapping(핸들러 매핑)의 BeanNameUrlHandlerMapping(스프링 빈이름으로 핸드러를 찾는다)에서 해당 URL 을 처리할 수 있는 핸들러 OldController 를 찾아 반환한다.
3. Controller 인터페이스를 실행할 수 있는 HandlerAdapter(핸들러 어댑터)를 찾아서 실행한다. -> 여기서는 SimpleControllerHandlerAdapter
 */
@Component("/springmvc/old-controller") //스프링 빈이름으로 핸들러를 찾기위해
public class OldController implements Controller { //
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}
