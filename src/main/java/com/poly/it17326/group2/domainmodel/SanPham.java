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
 * @author anhntnph27418
 * 
 */
@Entity
@Table(name = "SanPham")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SanPham implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "MaSP")
    private String ma;

    @Column(name = "TenSP")
    private String ten;
    
    @Column(name = "NgayTao")
    private String ngayTao;

    @Column(name = "NgaySua")
    private String ngaySua;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<ChiTietSP> listChiTietSP;
}
