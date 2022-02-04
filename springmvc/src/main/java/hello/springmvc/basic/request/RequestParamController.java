package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 서블릿에서 사용했던 방식
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * @ReqeustParam 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /**
     * @RequestParam 쿼리 파라미터명과 변수명이 같을 때 name 속성 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 파라미터가 String, int, Integer 등 단순 타입이면 @RequestParam 생략 가능
     *
     * @RequestParam 생략 시엔 require=false 와 같다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam required 속성으로 필수값 지정
     * required=false 에 프리미티브 타입을 사용하면 값이 들어오지 않을때 null 을 넣을 수 없기 때문에 500 에러 발생
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam defaultValue 속성 사용
     * 빈문자가 넘어와도 defaultValue 가 적용된다.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam Map 으로 파라미터 전체 받아오기
     * MultiValueMap 으로도 받아오기 가능. 같은 키에 값이 여러개 일 경우 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /**
     * @ModelAttriubet 동작 과정
     * 1. HelloData 객체 생성
     * 2. 요청 파라미터의 이름으로 helloData 객체의 프로퍼티를 찾아 setter 를 호출하여 파라미터 값을 입력(바인딩)한다.
     * 프로퍼티 : 어떤 필드가 getter, setter 를 가지고 있으면 해당 필드는 프로퍼티이다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("helloData={}", helloData);
        return "ok";
    }

    /**
     * @ModelAttribute 생략
     * 생략시 규칙
     * - String, int, Integer 와 같은 단순 타입 -> @RequestParam
     * - 나머지 -> @ModelAttribute(argument resolver 로 지정된 타입 제외)
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("helloData={}", helloData);
        return "ok";
    }
}

