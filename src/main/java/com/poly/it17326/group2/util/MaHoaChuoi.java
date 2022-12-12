/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.util;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author quynhncph26201
 */
public class MaHoaChuoi {

    public static String maHoaMd5(String password) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            result = myHash;
        } catch (Exception e) {
        }
        return result;
    }

    public static boolean verify(String inputPassword, String hashPassWord) {
        String myChecksum = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inputPassword.getBytes());
            byte[] digest = md.digest();
            myChecksum = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashPassWord.equals(myChecksum);
    }
}
