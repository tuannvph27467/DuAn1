package com.poly.it17326.group2.domainmodel;
//
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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ChiTietSP")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietSP implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;

    @Column(name = "MaChiTietSP")
    private String maChiTietSP;
    
    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "IdNCC")
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "IdThuongHieu")
    private ThuongHieu thuongHieu;

    @ManyToOne
    @JoinColumn(name = "IdKichThuoc")
    private KichThuoc kichThuoc;

    @ManyToOne
    @JoinColumn(name = "IdDeGiay")
    private DeGiay deGiay;

    @Column(name = "NgayTao")
    private String ngayTao;

    @Column(name = "NgaySua")
    private String ngaySua;

    @Column(name = "SoLuongTon")
    private Integer soLuongTon;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @Column(name = "Gia")
    private BigDecimal gia;

    @OneToMany(mappedBy = "chiTietSP", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<HoaDonChiTiet> listHDCT;

}
