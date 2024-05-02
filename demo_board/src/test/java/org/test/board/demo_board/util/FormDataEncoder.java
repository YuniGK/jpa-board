package org.test.board.demo_board.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@TestComponent
/* 테스트 설정을 위한 어노테이션, @Configuration과 다르지 않다.
* 차이점, 기본적으로 @SpringBootConfiguration에서 스캔하지 않는다.
*
* [ 사용하는 경우 ]
* @SpringBootTest 지정 클래스의 내부클래스에 지정 - @Import 를 사용하지 않아도 자동으로 설정이 로드
* 독립된 외부 클래스에 지정하고 @Import - 여러 테스트에서 공유해서 사용할 설정을 정의 */
public class FormDataEncoder {

    private final ObjectMapper mapper;

    public FormDataEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String encode(Object obj) {
        Map<String, String> fieldMap = mapper.convertValue(obj, new TypeReference<>() {});
        /* MultiValueMap - Map 인터페이스를 상속할 때 Value값을 List로 감싼 채로 상속
        * 하나의 Key와 하나 이상의 value로 이루어진 리스트를 쌍으로 받는다.(키의 중복이 허용, 값 순서보장)
        *
        * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/LinkedMultiValueMap.html */
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.setAll(fieldMap);

        return UriComponentsBuilder.newInstance()
                .queryParams(valueMap)
                .encode()
                .build()
                .getQuery();
    }
}
