package com.example.FamGift.category.facade;

import com.example.FamGift.category.dto.CategoryDto;
import com.example.FamGift.category.service.CategoryService;
import com.example.FamGift.common.service.CommonService;
import com.example.FamGift.common.service.JwtTokenServiceImpl;
import com.example.FamGift.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryFacade {
    private final CategoryService categoryService;
    private final CommonService commonService;
    public HttpStatus addCategory(CategoryDto dto) {
        String jwt = commonService.getCookieValueFromRequest("jwt");
        User categoryOwner = commonService.findUserByJwtToken(jwt);
        return categoryService.addCategory(dto, categoryOwner);
    }
}
