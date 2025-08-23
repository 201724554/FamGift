package com.example.FamGift;

import com.example.FamGift.gifticon.service.GifticonService;
import com.example.FamGift.user.model.Auth;
import com.example.FamGift.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GifticonServiceTest {
    @Autowired
    GifticonService gifticonService;

    @Test
    public void test1() {
        User user = new User(12L, "","", Auth.ROLE_USER);
        gifticonService.getGifticonByGroupRelatedToUser(user);
    }
}
