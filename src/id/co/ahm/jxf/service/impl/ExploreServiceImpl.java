/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.service.impl;

import id.co.ahm.jxf.service.ExploreService;
import org.springframework.stereotype.Service;

/**
 *
 * @author achmad.ha
 */
@Service("ExploreService")
public class ExploreServiceImpl implements ExploreService{
    
    public int tambah(int a,int b){
        return a+b;
    }
    
}
