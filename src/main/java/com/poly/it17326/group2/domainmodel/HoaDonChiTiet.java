/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author tuannvph27467
 */
@Entity
@Table(name = "HoaDonChiTiet")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTiet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdHoaDon", columnDefinition = "uniqueidentifier")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "IdChiTietSP", columnDefinition = "uniqueidentifier")
    private ChiTietSP chiTietSP;

    @Column(name = "MaSP")
    private String maSp;

    @Column(name = "TenSP")
    private String tenSp;

    @Column(name = "KichThuoc")
    private Integer kichThuoc;

    @Column(name = "MauSac")
    private String mauSac;

    @Column(name = "Gia")
    private BigDecimal gia;

    @Column(name = "SoLuong")
    private Integer soLuong;

}
