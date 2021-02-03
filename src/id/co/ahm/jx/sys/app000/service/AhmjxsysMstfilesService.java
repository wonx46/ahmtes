/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.service;

import id.co.ahm.jx.sys.app000.model.AhmjxsysMstfiles;
import id.co.ahm.jxf.vo.VoJxfUserCred;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author achmad.ha
 */
public interface AhmjxsysMstfilesService {
    
    public AhmjxsysMstfiles save(MultipartFile multipartFile, VoJxfUserCred voJxfUserCred, String idservice);
    public AhmjxsysMstfiles update(String vid,MultipartFile multipartFile, VoJxfUserCred voJxfUserCred, String idservice);
    public AhmjxsysMstfiles getByVid(String vid);
    
}
