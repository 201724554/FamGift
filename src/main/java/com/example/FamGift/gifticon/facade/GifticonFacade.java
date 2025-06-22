package com.example.FamGift.gifticon.facade;

import com.example.FamGift.category.model.Category;
import com.example.FamGift.common.model.CommonYn;
import com.example.FamGift.common.service.CommonService;
import com.example.FamGift.common.service.FileService;
import com.example.FamGift.gifticon.dto.GifticonAddDto;
import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon.dto.GifticonDto;
import com.example.FamGift.gifticon.service.GifticonService;
import com.example.FamGift.gifticon_category.model.GifticonCategory;
import com.example.FamGift.gifticon_category.service.GifticonCategoryService;
import com.example.FamGift.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GifticonFacade {
    private final CommonService commonService;
    private final GifticonService gifticonService;
    private final GifticonCategoryService gifticonCategoryService;
    private final FileService fileService;

    public List<GifticonDto> getGifticon() {
        User user = commonService.findUserByJwtToken();
        List<Gifticon> gifticons = gifticonService.getGifticon(user);
        return gifticons.stream().map(GifticonDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void addGifticon(MultipartFile image, GifticonAddDto dto) {
        //save gifticon
        String imagePath = fileService.getFilePath(image);
        User gifticonOwner = commonService.findUserByJwtToken();
        Gifticon gifticon = new Gifticon(dto, gifticonOwner, imagePath);
        gifticonService.saveGifticon(gifticon);

        //save gifticon-category
        List<GifticonCategory> gifticonCategories = dto.getCategories()
                .stream()
                .map((elem) -> new GifticonCategory(gifticon, new Category(elem))).collect(Collectors.toList());
        gifticonCategoryService.addGifticonCategories(gifticonCategories);

        //upload image file to the server
        if(!image.isEmpty()) {
            fileService.uploadFile(image, imagePath);
        }
    }

    public void deleteGifticon(Long id) {
        Gifticon gifticon = gifticonService.getGifticon(id).orElseThrow(NoSuchElementException::new);
        gifticon.delete();
        gifticonService.saveGifticon(gifticon);
    }

    public void useGifticon(Long id) {
        Gifticon gifticon = gifticonService.getGifticon(id).orElseThrow(NoSuchElementException::new);

        if(gifticon.getGifticonIsUsed().equals(CommonYn.Y)) {
            throw new RuntimeException("사용처리된 쿠폰입니다.");
        }
        if(gifticon.getGifticonUseYn().equals(CommonYn.N)) {
            throw new RuntimeException("삭제 처리된 쿠폰입니다.");
        }

        gifticon.use();
        gifticonService.saveGifticon(gifticon);
    }

    public void reuseGifticon(Long id) {
        Gifticon gifticon = gifticonService.getGifticon(id).orElseThrow(NoSuchElementException::new);

        if(gifticon.getGifticonIsUsed().equals(CommonYn.N)) {
            throw new RuntimeException("미사용처리된 쿠폰입니다.");
        }
        if(gifticon.getGifticonUseYn().equals(CommonYn.N)) {
            throw new RuntimeException("삭제 처리된 쿠폰입니다.");
        }

        gifticon.reuse();
        gifticonService.saveGifticon(gifticon);
    }
}
