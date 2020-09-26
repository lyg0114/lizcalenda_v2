package liz.kyle.calendal.dto;

import liz.kyle.calendal.domain.Member;
import liz.kyle.calendal.domain.bbs.Posts;
import lombok.Builder;

public class PostsSaveRequestDto {

    private String title;
    private String content;
    private Member member;

    @Builder
    public PostsSaveRequestDto(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
