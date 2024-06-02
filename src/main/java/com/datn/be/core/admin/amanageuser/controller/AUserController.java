package com.datn.be.core.admin.amanageuser.controller;

import com.datn.be.core.admin.amanageuser.model.request.AUserPostRequest;
import com.datn.be.core.admin.amanageuser.model.request.AUserPutRequest;
import com.datn.be.core.admin.amanageuser.model.request.AUserSearchRequest;
import com.datn.be.core.admin.amanageuser.service.AUserService;
import com.datn.be.infrastructure.constant.BaseUrl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_USER)
public class AUserController {

    private final AUserService userService;

    @PostMapping("/post-user")
    public ResponseEntity<?> postUser(@Valid @RequestBody AUserPostRequest request) {
        return ResponseEntity.ok(userService.postUser(request));
    }

    @PutMapping("/put-user/{id}")
    public ResponseEntity<?> putUser(@PathVariable String id, @Valid @RequestBody AUserPutRequest request) {
        return ResponseEntity.ok(userService.putUser(id, request));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.deletedUser(id));
    }

    @GetMapping("/detail-user/{id}")
    public ResponseEntity<?> detailUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.detailUser(id));
    }

    @GetMapping("/get-search-user")
    public ResponseEntity<?> getSearchUser(@Param("request") AUserSearchRequest request) {
        return ResponseEntity.ok(userService.getSearchUser(request));
    }

}
