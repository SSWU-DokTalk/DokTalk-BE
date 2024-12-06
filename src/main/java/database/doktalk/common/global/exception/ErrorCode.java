package database.doktalk.common.global.exception;

import database.doktalk.domain.worldcup.entity.WorldCup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON402", "금지된 요청입니다."),
    UNAUTHORIZED_MODIFY(HttpStatus.BAD_REQUEST, "COMMON403", "수정, 삭제 권한이 없습니다."),
    USER_NOT_ADMIN(HttpStatus.UNAUTHORIZED, "COMMON404", "관리자만 사용 가능한 API입니다."),
    UNKNOWN_INQUIRY_TYPE(HttpStatus.BAD_REQUEST, "COMMON405", "알 수 없는 조회 타입입니다."),

    //user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"USER401","사용자를 찾을 수 없습니다."),
    
    //book
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND,"BOOK401","책을 찾을 수 없습니다."),

    //worldCup
    WORLD_CUP_NOT_FOUND(HttpStatus.NOT_FOUND,"WORLD_CUP401","월드컵을 찾을 수 없습니다."),

    //discussion
    DISCUSSION_NOT_FOUND(HttpStatus.NOT_FOUND,"DISCUSSION_CUP401","토론을 찾을 수 없습니다."),

    //diary
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND,"DIARY_CUP401","기록장을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
