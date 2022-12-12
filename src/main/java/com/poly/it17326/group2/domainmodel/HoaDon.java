/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "HoaDon")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

//    @Column(name = "IdKM")
//    private String idKM;
    @ManyToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "IdNhanVien")
    private NhanVien nhanVien;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "NgayTao")
    private String ngayTao;

    @Column(name = "NgayThanhToan")
    private String ngayThanhToan;

    @Column(name = "NgaySua")
    private String ngaySua;

    @OneToOne
    @JoinColumn(name = "IdTrangThai")
    private TrangThai trangThai;

    @Column(name = "TienKhuyenMai")
    private BigDecimal tienKM;

    @Column(name = "TienTraLai")
    private BigDecimal tienTraLai;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "ThanhTien")
    private BigDecimal thanhTien;

    @Column(name = "LyDo")
    private String lyDo;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> listHDCT;
}
