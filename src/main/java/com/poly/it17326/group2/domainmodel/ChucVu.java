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
//hocnvph27417
@Entity
@Table(name = "ChucVu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChucVu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private String id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @OneToMany(mappedBy = "chucVu", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<NhanVien> listTaiKhoan;
}
