package com.jojoldu.admin.springboot.domain.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");
    //해당설정을 변경하려면 DB에 직접 접근해야함(ReadMe.txt 참고)
    private final String key;
    private final String title;
}
