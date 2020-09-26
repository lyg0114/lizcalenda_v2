package liz.kyle.calendal.dto;

import liz.kyle.calendal.domain.Member;
import liz.kyle.calendal.domain.bbs.Posts;

import java.time.LocalDateTime;

public class PostsListResponseDto {

    private Long id;
    private String title;
    private Member member;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.member = entity.getMember();
        this.modifiedDate = entity.getModifiedDate();
    }
}
