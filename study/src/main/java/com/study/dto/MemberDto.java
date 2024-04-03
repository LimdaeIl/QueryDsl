package com.study.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberDto {
    private String username;
    private int age;

    public MemberDto() {}

    @QueryProjection
    public MemberDto(String username, int age) {
        this.age = age;
        this.username =username;
    }
}
