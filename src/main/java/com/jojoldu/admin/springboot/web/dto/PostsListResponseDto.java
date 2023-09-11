package com.jojoldu.admin.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jojoldu.admin.springboot.service.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
//게시물 목록을 처리하는 부분
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;
//entity 매개변수로 각 변수에 수신된 정보를 저장함
    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }

}
