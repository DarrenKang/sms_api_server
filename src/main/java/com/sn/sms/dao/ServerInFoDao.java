package com.sn.sms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sn.sms.model.sms.ServerInFo;
import com.sn.sms.model.sms.SmsConfig;

@Repository
public class ServerInFoDao {
	 	@Autowired
	    private UniversalDao smsBaseDao;
	    @Autowired
	    private UniversalDao bisDao;
		public List<ServerInFo> getServerInFo(String plartFormId,String channel) {
			// TODO Auto-generated method stub
			System.out.println("查找当前正在查询的平台号为 " + plartFormId);
	        DetachedCriteria dc = DetachedCriteria.forClass(ServerInFo.class);
	        dc.add(Restrictions.eq("platformId",plartFormId));
	        dc.add(Restrictions.eq("channel",Integer.valueOf(channel)));
	        return (ArrayList<ServerInFo>) smsBaseDao.getHibernateTemplate().findByCriteria(dc);
		}
	    
}
