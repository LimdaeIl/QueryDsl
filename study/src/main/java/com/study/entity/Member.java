package com.study.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    private void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
    /**
     * , 양쪽 방향에 모두 값을 입력해주는 것이 가장 안전합니다.
     * 즉시 로딩 방법을 사용하여, 1차캐시에 영속화 되어있는 값을 그대로 가져오는 경우
     * 주인이 아닌쪽에서 주인 객체의 조회가 불가능 합니다.
     * 또한, 아래의 코드와 같이 순수한 객체 상태에서 심각한 문제가 발생할 수 있기 때문입니다.
     */
}
