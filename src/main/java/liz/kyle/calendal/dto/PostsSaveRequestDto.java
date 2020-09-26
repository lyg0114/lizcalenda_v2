package liz.kyle.calendal.dto;

import liz.kyle.calendal.domain.member.Member;
import liz.kyle.calendal.domain.bbs.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String userId;

    @Builder
    public PostsSaveRequestDto(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Posts toEntity(Member member) {

        return Posts.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
