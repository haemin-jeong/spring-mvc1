package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    /**
     * InputStream(Reader) 를 사용하여 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer) 를 사용하여 응답 메시지의 바디에 직접 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity
     * - http header, body 정보를 편리하게 조회할 수 있다.
     * - 응답에서 사용시 바디 정보를 직접 반환할 수 있다.
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * HttpEntity 를 상속하여 부가 기능이 더해진 RequestEntity, ResponseEntity 도 있다.
     * - RequestEntity : HttpMethod, url 정보가 추가, 요청에서 사용
     * - ResponseEntity : HTTP 상태 코드 설정 기능, 응답에서 사용
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody
     * - HTTP 메시지 바디 정보를 편리하게 조회할 수 있다.
     * - 헤더 정보가 필요하다면 HttpEntity 를 사용하거나 @RequestHeader 를 사용하면된다.
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * @ResponseBody
     * - 메시지 바디 정보를 직접 반환할 수 있다
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * 요청 파라미터 vs HTTP 메시지 바디
     * - 요청 파라미터 조회 -> @RequestParam, @ModelAttribute
     * - HTTP 바디 직접 조회 -> @RequestBody
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
