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

뷰 리졸버
스프링 부트는 여러 뷰 리졸버를 자동으로 등록하는데, 그 중 InternalResourceViewResolver 를 application.properties 에 등록한 spring.mvc.prefix, spring.mvc.suffix 설정 정보를 사용해서 등록한다.

뷰 리졸버 동작 과정
1. 핸들러 어댑터를 통해 new-form 이라는 논리 뷰 이름을 획득한다.
2. new-form 이라는 뷰 이름으로 viewResolver 를 순서대로 호출하고 그 중 InternalResourceViewResolver 가 호출 된다.
3. InternalResourceViewResolver 는 InternalResourceView 를 반환한다. -> InternalResourceView 는 JSP 처럼 forward 를 호출해서 처리하는 경우에 사용
4. view.render() 가 호출되고 InternalResourceView 는 forward() 를 사용해서 JSP 를 실행

참고
- InternalResourceViewResolver 는 JSTL 라이브러리가 있으면 InternalResourceView 를 상속받은 JstlView(JSTL 태그 사용시 약간의 부가 기능 추가) 를 반환.
- JSP 는 forward() 를 통해 렌더링이 되지만, 다른 뷰들은 포워드 과정 없이 바로 렌더링 된다.
- Thymeleaf 뷰 템플릿은 ThymeleafViewResolver 가 사용 된다.
 */
@Component("/springmvc/old-controller") //스프링 빈이름으로 핸들러를 찾기위해
public class OldController implements Controller { //
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}