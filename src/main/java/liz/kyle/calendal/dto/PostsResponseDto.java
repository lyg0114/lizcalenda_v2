package liz.kyle.calendal.dto;

import liz.kyle.calendal.domain.member.Member;
import liz.kyle.calendal.domain.bbs.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private Member member;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.member = entity.getMember();
    }
}
