package com.burgerplace.main.memberPage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberEntity {

    @Id
    private String id;

    private String pw;

    private String nickname;

    private boolean admin;

    private boolean delFlag;

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changePassword(String pw) {
        this.pw = pw;
    }

    public void changeAdmin(boolean admin) {
        this.admin = admin;
    }

    public void changeDel(boolean delFlag) {
        this.delFlag = delFlag;
    }
}
