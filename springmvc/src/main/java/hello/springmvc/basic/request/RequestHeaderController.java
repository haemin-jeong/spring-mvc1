package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod, //http 메서드 조회
            Locale locale, //Locale 정보 조회
            @RequestHeader MultiValueMap<String, String> headerMap, //전체 헤더 조회
            @RequestHeader("host") String host, //특정 헤더 조회
            @CookieValue(value = "myCookie", required = false) String cookie //특정 쿠키 조회
            ) {
        //MultiValueMap : map 과 유사하지만 하나의 키에 여러 값을 저장할 수 있다. key=value1&key=value2 와 같이 하나의 키에 여러 값이 들어올 때 사용

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
