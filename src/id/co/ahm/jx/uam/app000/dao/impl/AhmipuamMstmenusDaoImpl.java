/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao.impl;

import id.co.ahm.jx.uam.app000.dao.AhmipuamMstmenusDao;
import id.co.ahm.jx.uam.app000.model.AhmipuamMstmenus;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author achmad.ha
 */
@Repository("ahmipuamMstmenusDao")
public class AhmipuamMstmenusDaoImpl extends DefaultHibernateDao<AhmipuamMstmenus, Long>
        implements AhmipuamMstmenusDao {

    @Override
    public List<String> getCustomRole(String menuCode, String username) {
        String sqlQuery = "SELECT DISTINCT(VACTALLOWED) "
                + "FROM ahmipuam_mstroleaas  "
                + "WHERE IROLEACCSID IN (  "
                + "SELECT rlacs.iinternalid "
                + "FROM ahmipuam_mstuserrls usrrl , "
                + "ahmipuam_mstroles rl , "
                + "ahmipuam_mstroleaccs rlacs , "
                + "ahmipuam_mstmenus mn "
                + "WHERE usrrl.iroleintid = rl.iinternalid "
                + "AND rl.venableflag     = 'Y' "
                + "AND usrrl.vusername    = :username "
                + "AND rlacs.venableflag  = 'Y' "
                + "AND rlacs.iroleintid   = rl.iinternalid "
                + "AND rlacs.iroleintid   = usrrl.iroleintid "
                + "AND mn.venableflag     = 'Y' "
                + "AND mn.iinternalid     = rlacs.imenuintid "
                + "AND mn.vdeviceflag = 'J' "
                + "AND mn.vmenucode = :menuCode )";

        SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);
        query.setString("username", username);
        query.setString("menuCode", menuCode);
        List<String> list = query.list();

        return list;
    }

    @Override
    public List<String> getCustomRoleName(String username) {
        String sqlQuery = " SELECT distinct  rl.vrolename "
                + "   FROM ahmipuam_mstuserrls usrrl ,  "
                + "   ahmipuam_mstroles rl ,  "
                + "   ahmipuam_mstroleaccs rlacs ,  "
                + "   ahmipuam_mstmenus mn  "
                + "   WHERE usrrl.iroleintid = rl.iinternalid  "
                + "   AND rl.venableflag     = 'Y'  "
                + "   AND usrrl.vusername    = :username  "
                + "   AND rlacs.venableflag  = 'Y'  "
                + "   AND rlacs.iroleintid   = rl.iinternalid  "
                + "   AND rlacs.iroleintid   = usrrl.iroleintid  "
                + "   AND mn.venableflag     = 'Y'  "
                + "   AND mn.iinternalid     = rlacs.imenuintid  "
                + "   AND mn.vdeviceflag = 'J' ";

        SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);
        query.setString("username", username);
        List<String> list = query.list();

        return list;
    }

}
