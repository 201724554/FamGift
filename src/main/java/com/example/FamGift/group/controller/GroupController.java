package com.example.FamGift.group.controller;

import com.example.FamGift.common.controller.BaseController;
import com.example.FamGift.group.dto.GroupAddDto;
import com.example.FamGift.group.dto.GroupDto;
import com.example.FamGift.group.facade.GroupFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController implements BaseController {
    private final GroupFacade groupFacade;

    @GetMapping("/group")
    public ResponseEntity<List<GroupDto>> getGroup() {
        List<GroupDto> output = groupFacade.getGroupsByUser();
        return ResponseEntity.ok(output);
    }

    @PostMapping("/group")
    public ResponseEntity<String> addGroup(@RequestBody GroupAddDto dto) {
        groupFacade.addGroup(dto);
        return ResponseEntity.ok(null);
    }
}
