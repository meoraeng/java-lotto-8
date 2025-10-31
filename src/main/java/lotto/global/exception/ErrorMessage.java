package lotto.global.exception;

import static lotto.global.constants.NumberType.LOTTO_COST;
import static lotto.global.constants.NumberType.LOTTO_SIZE;

import java.text.MessageFormat;

public enum ErrorMessage {
    BLANK_INPUT_ERROR("빈 문자열이 감지되었습니다."),
    NOT_NUMBER_INPUT_ERROR("숫자만 입력 가능합니다."),
    INVALID_COST_ERROR(MessageFormat.format("{0, number}원 단위의 금액만 입력 가능합니다.", LOTTO_COST.getValue())),
    INVALID_SEPARATOR_ERROR("구분자 입력이 올바르지 않습니다."),
    INVALID_RANGE_ERROR("범위를 벗어난 입력입니다."),
    DUPLICATED_NUMBER_ERROR("숫자가 중복 되었습니다."),
    INVALID_LOTTO_SIZE_ERROR(MessageFormat.format("로또 번호는 총 {0}개 입니다.", LOTTO_SIZE));

    private final String message;
    ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
