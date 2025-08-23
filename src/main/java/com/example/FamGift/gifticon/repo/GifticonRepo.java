package com.example.FamGift.gifticon.repo;

import com.example.FamGift.common.model.CommonYn;
import com.example.FamGift.gifticon.dto.GifticonGroupDto;
import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GifticonRepo extends JpaRepository<Gifticon, Long> {
    List<Gifticon> findByGifticonOwnerAndGifticonUseYn(User owner, CommonYn useYn);
    List<Gifticon> findByGifticonOwnerAndGifticonUseYnAndGroup(User owner, CommonYn useYn, Group group);
    //카테고리를 여러 개 선택하면 모든 카레고리를 포함하는 경우에만 조회가 돼야 하는데 OR 방식으로 조회함
    @Query("SELECT DISTINCT g FROM " +
            "Gifticon g " +
            "JOIN g.gifticonCategories gc " +
            "JOIN gc.category c " +
            "WHERE g.gifticonOwner = :owner " +
            "AND c.id = :categoryIds " +
            "AND g.gifticonUseYn = :gifticonUseYn")
    List<Gifticon> findByOwnerIdAndCategoryIds(
            @Param("owner") User owner,
            @Param("categoryIds") Long categoryId,
            @Param("gifticonUseYn") CommonYn gifticonUseYn);

//    @Query("SELECT new com.example.FamGift.gifticon.dto.GifticonGroupDto(g, gp)" +
//            "FROM Gifticon g " +
//            "JOIN g.group gp " +
//            "JOIN UserGroup ug ON ug.group = gp " +
//            "WHERE ug.user = :user " +
//            "AND g.gifticonUseYn = :gifticonUseYn")
//    List<GifticonGroupDto> findByGroupRelatedToUser(
//            @Param("user") User user,
//            @Param("gifticonUseYn") CommonYn gifticonUseYn);

    /**
     * 위 방법처럼 객체 전체를 생성자로 던지면, 조회한 데이터 건 수 만큼 select 쿼리를 날림
     * 아래처럼 객체를 풀어서 생성자로 던져야 select 쿼리 하나로 데이터 조회
     * */
    @Query("SELECT new com.example.FamGift.gifticon.dto.GifticonGroupDto( " +
            "g.id, g.imagePath, g.brand, g.name, g.barcode, g.price, g.gifticonType, g.gifticonUsedDate, g.gifticonIsUsed, g.gifticonUseYn, g.expirationDate, g.gifticonOwner.id, " +
            "gp.id, gp.groupName, gp.groupAdmin.id, gp.groupAdmin.name " +
            ") " +
            "FROM Gifticon g " +
            "JOIN g.group gp " +
            "JOIN UserGroup ug ON ug.group = gp " +
            "WHERE ug.user = :user " +
            "AND g.gifticonUseYn = :gifticonUseYn")
    List<GifticonGroupDto> findByGroupRelatedToUser(
            @Param("user") User user,
            @Param("gifticonUseYn") CommonYn gifticonUseYn);

    @Query("SELECT new com.example.FamGift.gifticon.dto.GifticonGroupDto( " +
            "g.id, g.imagePath, g.brand, g.name, g.barcode, g.price, g.gifticonType, g.gifticonUsedDate, g.gifticonIsUsed, g.gifticonUseYn, g.expirationDate, g.gifticonOwner.id, " +
            "gp.id, gp.groupName, gp.groupAdmin.id, gp.groupAdmin.name " +
            ") " +
            "FROM Gifticon g " +
            "JOIN g.group gp " +
            "JOIN UserGroup ug ON ug.group = gp " +
            "WHERE ug.user = :user " +
            "AND g.gifticonUseYn = :gifticonUseYn " +
            "AND g.group.id = :groupId")
    List<GifticonGroupDto> findByGroupAndGroupRelatedToUser(
            @Param("user") User user,
            @Param("groupId") Long groupId,
            @Param("gifticonUseYn") CommonYn gifticonUseYn);
}
