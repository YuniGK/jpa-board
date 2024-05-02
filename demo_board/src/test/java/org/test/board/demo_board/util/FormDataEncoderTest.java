package org.test.board.demo_board.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("테스트 도구 - Form 데이터 인코더")

/* @SpringBootTest - 애플리케이션을 시작 테스트 수행에 필요한 빈을 자동으로 등록, 통합 테스트를 수행할 수 있도록 지원합니다.
 * 랜덤 포트를 사용하면 여러 개의 테스트에서 같은 서버를 공유할 수 있습니다.
 *
 * webEnvironment - 테스트의 웹 환경을 설정하는 속성
 * https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.WebEnvironment.html */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {FormDataEncoder.class, ObjectMapper.class}
)
class FormDataEncoderTest {
    private final FormDataEncoder formDataEncoder;

    FormDataEncoderTest(@Autowired FormDataEncoder formDataEncoder) {
        this.formDataEncoder = formDataEncoder;
    }

    @DisplayName("객체를 넣으면, url encoding 된 form body data 형식의 문자열을 돌려준다.")
    @Test
    void givenObject_whenEncoding_thenReturnsFormEncodedString() {
        // Given
        TestObject obj = new TestObject(
                "This 'is' \"test\" string.",
                List.of("hello", "my", "friend").toString().replace(" ", ""),
                String.join(",", "hello", "my", "friend"),
                null,
                1234,
                3.14,
                false,
                BigDecimal.TEN,
                TestEnum.THREE
        );

        // When
        String result = formDataEncoder.encode(obj);

        // Then
        assertThat(result).isEqualTo(
                "str=This%20'is'%20%22test%22%20string." +
                        "&listStr1=%5Bhello,my,friend%5D" +
                        "&listStr2=hello,my,friend" +
                        "&nullStr" +
                        "&number=1234" +
                        "&floatingNumber=3.14" +
                        "&bool=false" +
                        "&bigDecimal=10" +
                        "&testEnum=THREE"
        );
    }

    record TestObject(
            String str,
            String listStr1,
            String listStr2,
            String nullStr,
            Integer number,
            Double floatingNumber,
            Boolean bool,
            BigDecimal bigDecimal,
            TestEnum testEnum
    ) {}

    enum TestEnum {
        ONE, TWO, THREE
    }
}
