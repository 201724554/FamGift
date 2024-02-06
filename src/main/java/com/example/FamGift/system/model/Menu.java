package com.example.FamGift.system.model;

import com.example.FamGift.common.model.CommonEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "TB_MENU", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_NAME", columnNames = {"MENU_NAME"}),
        @UniqueConstraint(name = "UNIQUE_ORDER", columnNames = {"MENU_ORDER"}),
})
@Getter
public class Menu extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;
    @Column(name = "MENU_NAME", length = 30)
    private String menuName;
    @Column(name = "MENU_ORDER")
    private Integer menuOrder;
    @Column(name = "MENU_LINK", length = 40)
    private String menuLink;
}
