/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.domainmodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "TrangThai")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrangThai implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    @Column(name = "Ma")
    private String ma;
    
    @Column(name = "TrangThai")
    private Integer tenTrangThai;
    
    @Column(name = "NgayTao")
    private String ngayTao;

    @Column(name = "NgaySua")
    private String ngaySua;
}
