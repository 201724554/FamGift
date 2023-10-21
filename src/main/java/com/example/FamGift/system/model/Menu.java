package com.example.FamGift.system.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;
    @Column(name = "MENU_NAME", length = 30)
    private String menuName;
    @Column(name = "MENU_HIERARCHY_LEVEL")
    private Integer hierarchyLevel;
    @JoinColumn(name = "MENU_PARENT", foreignKey = @ForeignKey(name = "FK_MENU_TO_PARENT_MENU"))
    @OneToOne
    private Menu menuParent;
    @Column(name = "MENU_ORDER")
    private Integer menuOrder;
    @OneToOne
    @JoinColumn(name = "WINDOW_ID")
    private Window window;
}
