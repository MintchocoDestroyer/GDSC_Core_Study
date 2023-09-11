package com.jojoldu.admin.springboot.web;
import com.jojoldu.admin.springboot.web.dto.PostsResponseDto;
import com.jojoldu.admin.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.admin.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.jojoldu.admin.springboot.service.PostsService;

//웹페이지의 기능들을 사상(매핑)하는 부분
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")   //전송정보의 보안을 위해 Post방식으로 전송한다
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")   //이미 작성된 정보의 수정이므로 데이터를 '대체'하기 위해 Put방식으로 전송한다
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){return postsService.update(id, requestDto);}

    @GetMapping("/api/v1/posts/{id}")   //아이디를 찾는 부분은 개인정보가 포함되지 않으므로 Get방식으로 전송한다
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
