package com.jojoldu.admin.springboot.service;
import com.jojoldu.admin.springboot.service.posts.Posts;
import com.jojoldu.admin.springboot.service.posts.PostsRepository;
import com.jojoldu.admin.springboot.web.dto.PostsResponseDto;
import com.jojoldu.admin.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.admin.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jojoldu.admin.springboot.web.dto.PostsListResponseDto;
import java.util.List;
import java.util.stream.Collectors;

//게시판의 거시적 기능 구현
@RequiredArgsConstructor
@Service
public class PostsService {
    //@Transactional 어노테이션이 붙은 함수들의 리턴된 정보를 한 곳에 모아둠
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)//(Lambda Expression)람다표현식으로는
                .collect(Collectors.toList());//.map(posts -> new PostsListResponseDto(posts))가 된다

    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
