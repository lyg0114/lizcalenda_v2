package liz.kyle.calendal.service;

import liz.kyle.calendal.domain.member.Member;
import liz.kyle.calendal.domain.bbs.Posts;
import liz.kyle.calendal.dto.PostsListResponseDto;
import liz.kyle.calendal.dto.PostsResponseDto;
import liz.kyle.calendal.dto.PostsSaveRequestDto;
import liz.kyle.calendal.dto.PostsUpdateRequestDto;
import liz.kyle.calendal.repository.MemberRepository;
import liz.kyle.calendal.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {


    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;



    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Member member = memberRepository.
                findByUserId(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 userid 가 없습니다."));

        return postsRepository.save(requestDto.toEntity(member)).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){

        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());

    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);

    }
}
