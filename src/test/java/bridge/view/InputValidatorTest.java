package bridge.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {
    @Nested
    @DisplayName("다리 길이에 대한 검증 테스트")
    class ValidateSizeTest {
        @ParameterizedTest(name = "입력값 -> {0}")
        @ValueSource(strings = {"eng", "한글", "!", "3.5"})
        @NullAndEmptySource
        @DisplayName("사용자 입력이 자연수가 아니면 예외가 발생한다.")
        void toNumber_SizeIsNotNumeric_ExceptionThrown(String input) {
            assertThatThrownBy(() -> new InputValidator().validateSize(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest(name = "입력값 -> {0}")
        @ValueSource(strings = {"2", "21"})
        @DisplayName("사용자 입력이 3과 20 사이의 자연수가 아니면 예외가 발생한다.")
        void validateRangeOfSize_SizeIsNotNumeric_ExceptionThrown(String input) {
            assertThatThrownBy(() -> new InputValidator().validateSize(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

}