package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.domain.park.ParkRepository;
import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.dto.BookmarkResponseDto;
import com.hangangFlow.hangangFlow.dto.BookmarkSaveDto;
import com.hangangFlow.hangangFlow.service.BookmarkService;
import com.hangangFlow.hangangFlow.service.BookmarkServiceImpl;
import com.hangangFlow.hangangFlow.domain.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

//@RequiredArgsConstructor
@Lazy
@RestController
@RequestMapping("/api")
public class BookmarkController {

//    private final BookmarkServiceImpl bookmarkServiceImpl;
//    private final UserRepository userRepository;
//    private final ParkRepository parkRepository;
    @Resource
    private final BookmarkService bookmarkService;
    private final UserRepository userRepository;
    @Autowired
    public BookmarkController(BookmarkService bookmarkService,UserRepository userRepository) {
        this.bookmarkService = bookmarkService;
        this.userRepository = userRepository;
    }


    //북마크 추가
    @PostMapping("/bookmark/{parkUuid}")
    public void SaveBookmark(@PathVariable UUID parkUuid,
                             @RequestBody BookmarkSaveDto bookmarkSaveDto) {
        bookmarkService.saveBookmark(bookmarkSaveDto.getUserUuid(), parkUuid);
    }

    //북마크 조회(유저 마이페이지에서 리스트 형태로 조회)
    @GetMapping("/bookmark/{userUuid}")
    public List<BookmarkResponseDto> getBookmarkList(@PathVariable UUID userUuid) {
        //User user = userRepository.findByUserUuid(userUuid);
        User user = userRepository.getReferenceById(userUuid);
        return bookmarkService.findAllBookmark(user.getUserUuid());
    }

    //북마크 삭제
    @DeleteMapping("/bookmark/{bookmarkUuid}")
    public void DeleteBookmark(@PathVariable UUID bookmarkUuid) {
        bookmarkService.deleteBookmark(bookmarkUuid);
    }
}
