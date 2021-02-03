/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.main;

import id.co.ahm.jxf.security.TokenPstUtil;

/**
 *
 * @author achmad.ha
 */
public class MainToken {
    
    public static void main(String[] args) {
        String token = "";
        String url = "/jx01/jxf-pst-ahmsvjxuam000/jx/uam052/get-all/jx01/jxf-pst-ahmsvjxuam000/jx/uam049/edit/jx01/jxf-pst-ahmsvjxuam000/jx/uam049/save/jx01/jxf-pst-ahmsvjxuam000/jx/uam050/get-all/jx01/jxf-pst-ahmsvjxuam000/jx/uam050/save/jx01/jxf-pst-ahmsvjxuam000/jx/uam051/get-all/jx01/jxf-pst-ahmsvjxuam000/jx/uam051/save/jx01/jxf-pst-ahmsvjxuam000/jx/uam052/save/jx01/jxf-pst-ahmsvjxuam000/jx/uam053/edit/jx01/jxf-pst-ahmsvjxuam000/jx/uam053/save/jx01/jxf-pst-ahmsvjxuam000/jx/uam053/get-all/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/get-all/jx01/jxf-pst-ahmsvjxuam000/jx/uam049/get-all/jx01/jxf-pst-ahmsvjxuam000/jx/uam052/edit/jx01/jxf-pst-ahmsvjxuam000/jx/uam050/edit/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/save-from-ext/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/save-from-karyawan/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/get-all-external/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/get-all-karyawan/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/save/jx01/jxf-pst-ahmsvjxuam000/jx/uam051/edit/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/edit/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/disable/jx01/jxf-pst-ahmsvjxuam000/jx/uam048/disable/jx01/jxf-pst-ahmsvjxuam000/jx/uam047/reset-password/jx01/jxf-pst-ahmsvjxuam000/jx/uam048/save/jx01/jxf-pst-ahmsvjxuam000/jx/uam048/get-all/jx01/jxf-pst-ahmsvjxuam000/jx/uam048/save/jx01/jxf-pst-ahmsvjxuam000/jx/uam048/edit";
        TokenPstUtil tokenPstUtil = new TokenPstUtil();
        tokenPstUtil.getUserCred(token);
        
        String input  = "5+3";
        
    }
    
}
