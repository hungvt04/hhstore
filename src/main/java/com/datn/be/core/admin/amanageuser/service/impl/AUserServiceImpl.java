package com.datn.be.core.admin.amanageuser.service.impl;

import com.datn.be.core.admin.amanageuser.model.request.AUserPostRequest;
import com.datn.be.core.admin.amanageuser.model.request.AUserPutRequest;
import com.datn.be.core.admin.amanageuser.model.request.AUserSearchRequest;
import com.datn.be.core.admin.amanageuser.model.response.AUserSearchResponse;
import com.datn.be.core.admin.amanageuser.repository.AUserRepository;
import com.datn.be.core.admin.amanageuser.service.AUserService;
import com.datn.be.entity.User;
import com.datn.be.infrastructure.common.PageResponse;
import com.datn.be.infrastructure.common.ResponseObject;
import com.datn.be.infrastructure.constant.enums.EnumTypeRole;
import com.datn.be.infrastructure.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AUserServiceImpl implements AUserService {

    private final AUserRepository userRepository;

    @Override
    public ResponseObject postUser(AUserPostRequest request) {
        List<String> errors = new ArrayList<>();
        boolean isDuplicateCccd = userRepository.existsByCccd(request.getCccd());
        boolean isDuplicateEmail = userRepository.existsByEmail(request.getEmail());
        boolean isDuplicatePhone = userRepository.existsByPhone(request.getPhone());

        if (isDuplicateCccd) {
            errors.add("CCCD is existed.");
        }
        if (isDuplicateEmail) {
            errors.add("Email is existed.");
        }
        if (isDuplicatePhone) {
            errors.add("Phone is existed.");
        }

        if (errors.isEmpty()) {
            User user = new User();
            user.setName(request.getName());
            user.setCccd(request.getCccd());
            user.setBirthDay(request.getBirthDay());
            user.setGender(request.getGender());
            user.setProvince(request.getProvince());
            user.setDistrict(request.getDistrict());
            user.setWard(request.getWard());
            user.setAddressDetail(request.getAddressDetail());

            EnumTypeRole role = EnumTypeRole.valueOf(request.getRole());
            user.setRole(role);

            user.setPhone(request.getPhone());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setImageId(request.getImageId());
            user.setImageUrl(request.getImageUrl());

            return ResponseObject.builder()
                    .data(userRepository.save(user))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject putUser(String id, AUserPutRequest request) {
        User user = (User) this.detailUser(id).getData();

        List<String> errors = new ArrayList<>();
        boolean isDuplicateCccd = userRepository.existsByCccd(request.getCccd());
        boolean isDuplicateEmail = userRepository.existsByEmail(request.getEmail());
        boolean isDuplicatePhone = userRepository.existsByPhone(request.getPhone());

        if (user.getCccd().equalsIgnoreCase(request.getCccd())) {
            isDuplicateCccd = false;
        }
        if (user.getEmail().equalsIgnoreCase(request.getEmail())) {
            isDuplicateEmail = false;
        }
        if (user.getPhone().equalsIgnoreCase(request.getPhone())) {
            isDuplicatePhone = false;
        }

        if (isDuplicateCccd) {
            errors.add("CCCD is existed.");
        }
        if (isDuplicateEmail) {
            errors.add("Email is existed.");
        }
        if (isDuplicatePhone) {
            errors.add("Phone is existed.");
        }

        if (errors.isEmpty()) {
            user.setName(request.getName());
            user.setCccd(request.getCccd());
            user.setBirthDay(request.getBirthDay());
            user.setGender(request.getGender());
            user.setProvince(request.getProvince());
            user.setDistrict(request.getDistrict());
            user.setWard(request.getWard());
            user.setAddressDetail(request.getAddressDetail());

            EnumTypeRole role = EnumTypeRole.valueOf(request.getRole());
            user.setRole(role);

            user.setPhone(request.getPhone());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setImageId(request.getImageId());
            user.setImageUrl(request.getImageUrl());

            return ResponseObject.builder()
                    .data(userRepository.save(user))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject deletedUser(String id) {
        User user = (User) this.detailUser(id).getData();
        user.setIsDeleted(!user.getIsDeleted());

        return ResponseObject.builder()
                .data(userRepository.save(user))
                .build();
    }

    @Override
    public ResponseObject detailUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RestException(List.of("Not found user with id: " + id));
        }
        return ResponseObject.builder()
                .data(userOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchUser(AUserSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<AUserSearchResponse> page = userRepository.getSearchUser(pageable, request);
        PageResponse<AUserSearchResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
