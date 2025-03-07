package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.domain.park.ParkRepository;
import com.hangangFlow.hangangFlow.domain.user.User;
import com.hangangFlow.hangangFlow.domain.park.Parks;
import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.dto.*;
import com.hangangFlow.hangangFlow.service.CommunityService;
import com.hangangFlow.hangangFlow.vo.CommunityListParkVO;
import com.hangangFlow.hangangFlow.vo.CommunityListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

//@RequiredArgsConstructor
@RestController
@Lazy
@RequestMapping("/api")

public class CommunityController {
    @Resource
    private final CommunityService communityService;
    private final UserRepository userRepository;
    private final ParkRepository parkRepository;

    @Autowired
    public CommunityController(CommunityService communityService, UserRepository userRepository, ParkRepository parkRepository) {
        this.communityService = communityService;
        this.userRepository = userRepository;
        this.parkRepository = parkRepository;
    }

    //게시글 작성
    @PostMapping("/community/{parkUuid}/article/create")
    public void saveCommunity(@PathVariable UUID parkUuid,
                     @RequestBody CommunitySaveDto article) {
        User user = userRepository.getReferenceById(article.getUserUuid());
        Parks parks = parkRepository.getReferenceById(parkUuid);
       communityService.saveCommunity(user, parks, article);
    }

    //게시글 내용 수정
    @PutMapping("/community/{communityUuid}/article/edit")
    public void updateCommunity(@PathVariable UUID communityUuid,
                       @RequestBody CommunityUpdateRequestDto article) {
        communityService.updateCommunity(communityUuid, article);
    }

    //게시글 삭제
    @DeleteMapping("/community/{communityUuid}/article/delete")
    public void deleteCommunity(@PathVariable UUID communityUuid) {
       communityService.deleteCommunity(communityUuid);
    }

    //게시글 내용 조회
    @GetMapping("/community/{communityUuid}/article")
    public CommunityResponseDto findCommunity (@PathVariable UUID communityUuid){
        return communityService.findCommunity(communityUuid);
    }

    //최신순 게시글 목록 조회
    @GetMapping("/community/article")
    public List<CommunityListVO> getCommunityList() {
        return communityService.findAllDesc();
    }


    //카테고리별 게시글 목록 조회
    @GetMapping("/community/{parkUuid}/article")
    public List<CommunityListParkVO> getCommunityParkList(@PathVariable UUID parkUuid) {
        Parks parks = parkRepository.getById(parkUuid);
        return communityService.findAllPark(parks.getParkName());
    }



//    //게시글 작성
//    @PostMapping("/community/{parkUuid}/article")
//    public UUID save(@PathVariable UUID parkUuid,
//                     @RequestBody CommunitySaveDto article) {
//        User user = userRepository.getById(article.getUserUuid());
//        Parks parks = parkRepository.getById(parkUuid);
//        return communityService.saveCommunity(user, parks, article);
//    }
//    //게시글 내용 수정
//    @PutMapping("/community/{id}/article")
//    public UUID update(@PathVariable UUID communityUuid,
//                       @RequestBody CommunityUpdateRequestDto article) {
//        return communityService.updateCommunity(communityUuid, article);
//    }
//
//    //게시글 삭제
//    @DeleteMapping("/community/{id}/article")
//    public UUID delete(@PathVariable UUID communityUuid) {
//
//        return communityService.deleteCommuity(communityUuid);
//    }







}
