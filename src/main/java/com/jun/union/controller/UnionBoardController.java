package com.jun.union.controller;

import com.jun.union.dto.ResponseDataDTO;
import com.jun.union.service.unionboard.UnionBoardDTO;
import com.jun.union.service.unionboard.UnionBoardService;
import com.jun.union.service.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/union/board")
public class UnionBoardController {

    private final UnionBoardService unionBoardService;

    @PostMapping("/save")
    public ResponseDataDTO<UnionBoardDTO> save(@RequestBody UnionBoardDTO dto) {
        return unionBoardService.save(dto);
    }
}
