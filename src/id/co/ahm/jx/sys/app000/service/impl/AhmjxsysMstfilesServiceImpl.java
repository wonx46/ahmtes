/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.service.impl;

import id.co.ahm.jx.sys.app000.dao.AhmjxsysMstfilesDao;
import id.co.ahm.jx.sys.app000.model.AhmjxsysMstfiles;
import id.co.ahm.jx.sys.app000.service.AhmjxsysMstfilesService;
import id.co.ahm.jxf.vo.VoJxfUserCred;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author achmad.ha
 */
@Service(value = "ahmjxsysMstfilesService")
@Transactional(readOnly = true)
public class AhmjxsysMstfilesServiceImpl implements AhmjxsysMstfilesService {
    
    @Autowired
    @Qualifier(value = "ahmjxsysMstfilesDao")
    private AhmjxsysMstfilesDao ahmjxsysMstfilesDao;
    
    @Override
    @Transactional(readOnly = false)
    public AhmjxsysMstfiles save(MultipartFile multipartFile, VoJxfUserCred voJxfUserCred, String idservice) {
        try {
            AhmjxsysMstfiles ahmjxsysMstfiles = new AhmjxsysMstfiles();
            ahmjxsysMstfiles.setBfilecontent(multipartFile.getBytes());
            ahmjxsysMstfiles.setNfilesize(multipartFile.getSize());
            ahmjxsysMstfiles.setVfilename(multipartFile.getOriginalFilename());
            ahmjxsysMstfiles.setVmimetype(multipartFile.getContentType());
            ahmjxsysMstfilesDao.save(ahmjxsysMstfiles, voJxfUserCred.getUsername(), idservice);
            return ahmjxsysMstfiles;
        } catch (IOException ex) {
            Logger.getLogger(AhmjxsysMstfilesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    @Transactional(readOnly = false)
    public AhmjxsysMstfiles update(String vid, MultipartFile multipartFile, VoJxfUserCred voJxfUserCred, String idservice) {
        AhmjxsysMstfiles ahmjxsysMstfiles = ahmjxsysMstfilesDao.findOne(vid);
        if (ahmjxsysMstfiles != null) {
            try {                
                ahmjxsysMstfiles.setBfilecontent(multipartFile.getBytes());
                ahmjxsysMstfiles.setNfilesize(multipartFile.getSize());
                ahmjxsysMstfiles.setVfilename(multipartFile.getOriginalFilename());
                ahmjxsysMstfiles.setVmimetype(multipartFile.getContentType());
                ahmjxsysMstfilesDao.update(ahmjxsysMstfiles, voJxfUserCred.getUsername(), idservice);
                return ahmjxsysMstfiles;
            } catch (IOException ex) {
                Logger.getLogger(AhmjxsysMstfilesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public AhmjxsysMstfiles getByVid(String vid) {
        return ahmjxsysMstfilesDao.findOne(vid);
    }

    public AhmjxsysMstfilesDao getAhmjxsysMstfilesDao() {
        return ahmjxsysMstfilesDao;
    }

    public void setAhmjxsysMstfilesDao(AhmjxsysMstfilesDao ahmjxsysMstfilesDao) {
        this.ahmjxsysMstfilesDao = ahmjxsysMstfilesDao;
    }
}
