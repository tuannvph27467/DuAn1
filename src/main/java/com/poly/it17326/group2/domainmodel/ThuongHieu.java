/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.domainmodel;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author PC chienđqph25320
 */
@Entity
@Table(name = " ThuongHieu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ThuongHieu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "thuongHieu", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<ChiTietSP> listChiTietSP;
    
}
