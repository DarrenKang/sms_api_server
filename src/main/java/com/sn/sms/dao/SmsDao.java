package com.sn.sms.dao;

import com.sn.sms.model.Constants;
import com.sn.sms.model.bean.websocket.req.send.SendMsgReq;
import com.sn.sms.model.sms.*;
import com.sn.sms.utils.DateUtils;
import com.sn.sms.utils.Page;
import com.sn.sms.utils.PageQuery;
import com.sn.sms.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 10/25/16.
 */

@Repository
public class SmsDao {

    @Autowired
    private UniversalDao smsBaseDao;
    @Autowired
    private UniversalDao bisDao;


    public String modifyAccount(String loginname, String password){
    	String msg=null;
        SmsAccount smsAccount = (SmsAccount) smsBaseDao.get(SmsAccount.class, loginname);
        smsAccount.setLoginname(loginname);
        smsAccount.setPassword(password);
        smsBaseDao.update(smsAccount);
        msg = "MODIFYACCOUNT";
        return msg;
    }

    public String addAccount(SmsAccount smsAccount){
    	String msg=null;
    	 smsBaseDao.saveOrUpdate(smsAccount); 
    	 msg="ADDACOUNT";
    	 return msg;
    	 }

    public SmsAccount getSmsAccount(String loginname, String password){

        SmsAccount smsAccount = (SmsAccount) smsBaseDao.get(SmsAccount.class, loginname);

        if(smsAccount!= null){
            if(password.equals(smsAccount.getPassword()))
                return smsAccount;
        }

        return null;
    }

    public void saveSentSMS(SmsMessages smsMessages){
        bisDao.save(smsMessages);
    }
    public void saveWsSentSMS(SmsMessages smsMessages){
        smsBaseDao.save(smsMessages);
    }

    public ArrayList<SmsConfig> getSmsConfig(SendMsgReq req) {
        System.out.println("查找当前正在使用配置的参数为 " + req);
        DetachedCriteria dc = DetachedCriteria.forClass(SmsConfig.class);
        dc.add(Restrictions.eq("channel", Integer.parseInt(req.getChannel())));
        dc.add(Restrictions.eq("platformIds", req.getPlatformId()));
        dc.add(Restrictions.eq("flag", 0));
        dc.add(Restrictions.eq("flag1", 0));
        return (ArrayList<SmsConfig>) smsBaseDao.getHibernateTemplate().findByCriteria(dc);
    }
    public List<SmsConfig> getsmsConfig(SendMsgReq req,String priority) {
        System.out.println("query data " + req);
        DetachedCriteria dc = DetachedCriteria.forClass(SmsConfig.class);
        dc.add(Restrictions.eq("channel", Integer.parseInt(req.getChannel())));
        dc.add(Restrictions.like("platformIds", req.getPlatformId(), MatchMode.ANYWHERE));
        dc.add(Restrictions.eq("priority",priority));
        dc.add(Restrictions.eq("flag1", 0));
        return (List<SmsConfig>) smsBaseDao.getHibernateTemplate().findByCriteria(dc);
    }
    
    public List<SmsConfig> getSmsConfig(SendMsgReq req,String priority) {
        System.out.println("query data " + req);
        DetachedCriteria dc = DetachedCriteria.forClass(SmsConfig.class);
        dc.add(Restrictions.eq("channel", Integer.parseInt(req.getChannel())));
        dc.add(Restrictions.like("platformIds", req.getPlatformId(), MatchMode.ANYWHERE));
        dc.add(Restrictions.eq("flag", 0));
        dc.add(Restrictions.eq("priority",priority));
        dc.add(Restrictions.eq("flag1", 0));
        return (List<SmsConfig>) smsBaseDao.getHibernateTemplate().findByCriteria(dc);
    }
    public List<SmsConfig> getAllSmsConfig(SendMsgReq req) {
        System.out.println("query data " + req);
        DetachedCriteria dc = DetachedCriteria.forClass(SmsConfig.class);
        dc.add(Restrictions.eq("channel", Integer.parseInt(req.getChannel())));
       // dc.add(Restrictions.like("platformIds", req.getPlatformId(), MatchMode.ANYWHERE));
        dc.add(Restrictions.eq("platformIds", req.getPlatformId()));
        dc.add(Restrictions.eq("flag1", 0));
        return (List<SmsConfig>) smsBaseDao.getHibernateTemplate().findByCriteria(dc);
    }
    
    public List<Integer> getChanneList() {
        DetachedCriteria dc = DetachedCriteria.forClass(SmsConfig.class);
        dc.setProjection(Projections.distinct(Projections.property("channel")));
        return smsBaseDao.findByCriteria(dc);
    }

    public void saveSmsLog(String apiResp, String content, String phone, String platform, String smsName, String provider) {
        SmsLog log = new SmsLog();
        log.setPlatform(platform);
        log.setProvider(provider);
        log.setSmsName(smsName);
        log.setPhoneNumber(phone);
        log.setContent(content);
        log.setApiResponse(apiResp);
        smsBaseDao.save(log);
    }

    /**
     * add sms config
     */
    public String addSmsConfig(String type, String user, String server, String remark, Integer port, String[] platformIds, String password, String name, Integer flag, Integer channel,String priority,String totalCounts){
        String msg = null;
        String projects = null;
        if(platformIds != null){
            projects = StringUtil.arrayToString(platformIds);
        }

        SmsConfig smsConfig = (SmsConfig) smsBaseDao.get(SmsConfig.class, name);
        if(smsConfig == null){
            SmsConfig config = new SmsConfig(type, user, server, remark, port, projects, password, name, flag, channel, DateUtils.parseDateForStandard(),priority,totalCounts,"0",0);
            config.setType(type);
            smsBaseDao.save(config);
            msg = "SMSCONFIG_ADDED";
        }else{
            msg = "SMSCONFIG_EXISTS";
        }
        return msg;
    }
    
    /**
     * delete sms config
     */
    public String deleteSmsConfig(String username, String name){
        String msg = null;
        SmsConfig smsConfig = (SmsConfig) smsBaseDao.get(SmsConfig.class, username);
        if(smsConfig != null){
            smsBaseDao.delete(smsConfig);
            msg = "SMSCONFIG_DELETED";
        }
        return msg;
    }
    
    /**
     * update sms config
     */
    public String updateSmsConfig(String type, String user, String server, String remark, Integer port, String[] platformIds, String password, String name, Integer flag, Integer channel,String priority,String totalCounts){
        String msg = null;
        String projects = null;
        if(platformIds != null){
            projects = StringUtil.arrayToString(platformIds);
        }
        SmsConfig smsConfig = (SmsConfig) smsBaseDao.get(SmsConfig.class, name);
        if(smsConfig != null){

            if(type != null)
                smsConfig.setType(type);

            if(server != null)
                smsConfig.setServer(server);

           if(port != null)
               smsConfig.setPort(port);

            if(user != null)
                smsConfig.setUser(user);

            if(channel != null)
                smsConfig.setChannel(channel);

            if(remark != null)
                smsConfig.setRemark(remark);

            if (StringUtil.isNotBlank(password)) {
                smsConfig.setPassword(password);
            }

            if (StringUtil.isNotBlank(projects)) {
                smsConfig.setPlatformIds(projects);
            }
            
            	smsConfig.setPriority(priority);
            if(StringUtil.isNotEmpty(totalCounts)){
            	smsConfig.setTotalCounts(totalCounts);
            }

            smsBaseDao.update(smsConfig);
            msg = "SMSCONFIG_UPDATED";
        }
        return msg;
    }

    /**
     * enable sms config
     */
    public String enableConfig(String name){
        String msg = null;
        SmsConfig smsConfig = (SmsConfig) smsBaseDao.get(SmsConfig.class, name);
        if(smsConfig != null){
            smsConfig.setFlag(Constants.ENABLE);
            smsConfig.setSwitchTime(new Date());
            smsBaseDao.update(smsConfig);
            msg = "SMSCONFIG_ENABLED";
        }
        return msg;
    }

    /**
     * disable sms config
     */
    public String disableConfig(String name){
        String msg = null;
        SmsConfig smsConfig = (SmsConfig) smsBaseDao.get(SmsConfig.class, name);
        if(smsConfig != null){
            smsConfig.setFlag(Constants.DISABLE);
            smsConfig.setSwitchTime(new Date());
            smsBaseDao.update(smsConfig);
            msg = "SMSCONFIG_DISABLED";
        }
        return msg;
    }
    public Page querySmsContents(String product, String code, Date dateStart, Date dateEnd, Integer index, Integer size, String accountId){

        System.out.println("product = [" + product + "], code = [" + code + "], dateStart = [" + dateStart + "], dateEnd = [" + dateEnd + "], index = [" + index + "], size = [" + size + "], accountId = ["+accountId+"]");
        List<SmsMessages> list = null;
        DetachedCriteria dc = DetachedCriteria.forClass(SmsMessages.class);
        if(StringUtil.isNotEmpty(product))
            dc.add(Restrictions.eq("product",product));
        if(dateStart != null)
            dc.add(Restrictions.gt("createtime",dateStart));
        if(dateEnd != null)
            dc.add(Restrictions.lt("createtime",dateEnd));
        if(StringUtil.isNotEmpty(code))
            dc.add(Restrictions.eq("smsprovider",code));
        if(StringUtil.isNotEmpty(accountId)){
        	dc.add(Restrictions.eq("user_account", accountId));
        }
        dc.addOrder(Order.desc("createtime"));

        return PageQuery.queryForPagenation(smsBaseDao.getHibernateTemplate(), dc, index, size);
    }

    public List<SmsConfig> querySmsConfigByProject(String name, String type, String platformIds, Integer flag,Integer channel,String sortField,String orderRule){
        DetachedCriteria dc = DetachedCriteria.forClass(SmsConfig.class);
        if(StringUtil.isNotEmpty(name)){
            dc.add(Restrictions.eq("name", name));
        }
        if(StringUtil.isNotEmpty(type)){
            dc.add(Restrictions.eq("type", type));
        }
        if(flag != null){
            dc.add(Restrictions.eq("flag", flag));
        }
        if(StringUtil.isNotEmpty(platformIds)){
            dc.add(Restrictions.eq("platformIds", platformIds));
        }
        if(channel!=null){
        	dc.add(Restrictions.eq("channel",channel));
        }
        if(StringUtils.isNotEmpty(orderRule) && StringUtils.isNotEmpty(sortField)){
        	if("asc".equals(orderRule)){
        		dc.addOrder(Order.asc(sortField));
        	}
        	if("desc".equals(orderRule)){
        		dc.addOrder(Order.desc(sortField));
        	}
        }
        return  (List<SmsConfig>) smsBaseDao.findByCriteria(dc);
    }
	public void updateSmsConfigFlag(String name,Integer flag,Date swithTime,String areadyCounts,Integer flag1) {
		// TODO Auto-generated method stub
		System.out.println("更改配置配置参数为");
		SmsConfig smsConfig = (SmsConfig) smsBaseDao.get(SmsConfig.class, name);
		if(smsConfig!=null){
			if(flag!=null){
				smsConfig.setFlag(flag);
			}
			if(swithTime!=null){
				smsConfig.setSwitchTime(swithTime);
			}
			if(areadyCounts!=null){
				smsConfig.setAreadyCounts(areadyCounts);
			}
			if(flag1!=null){
				smsConfig.setFlag1(flag1);
			}
			 smsBaseDao.update(smsConfig);
		}
	}
	public SmsMessages querySmsContent(String phonenumber,String user_account){
		 System.out.println("phonenumber = [" + phonenumber + "], user_account = [" + user_account + "]");
	        List<SmsMessages> list = null;
	        DetachedCriteria dc = DetachedCriteria.forClass(SmsMessages.class);
	        if(phonenumber != null)
	            dc.add(Restrictions.eq("phonenumber",phonenumber));
	        if(user_account != null)
	            dc.add(Restrictions.eq("user_account",user_account));
	        dc.addOrder(Order.desc("createtime"));
	        
	        List<SmsMessages>  smsMessages=smsBaseDao.findByCriteria(dc);
	        return smsMessages.get(0);
	}
	public String cancelDisableSmsConfig(String name){
		String msg=null;
		SmsConfig smsConfig = (SmsConfig) smsBaseDao.get(SmsConfig.class, name);
		if(smsConfig!=null){
			smsConfig.setAreadyCounts("0");
			smsConfig.setFlag1(0);
			smsBaseDao.update(smsConfig);
			 msg = "SMSCANCELDISABLED";
		}
		return msg;
	}
	public void saveEntity(SmsConfigLog smsConfigLog){
		smsBaseDao.save(smsConfigLog);
	}
	public Page querySMSLogsContents(String operator, String platformIds, Date dateStart, Date dateEnd, Integer index, Integer size){
		System.out.println("platformIds = [" + platformIds + "], operator = [" + operator + "], dateStart = [" + dateStart + "], dateEnd = [" + dateEnd + "], index = [" + index + "], size = [" + size + "]");
		DetachedCriteria dc=DetachedCriteria.forClass(SmsConfigLog.class);
		if(StringUtil.isNotEmpty(operator)){
			dc.add(Restrictions.eq("operator", operator));
		}
		if(StringUtil.isNotEmpty(platformIds)){
			dc.add(Restrictions.eq("platformIds", platformIds));
		}
		if(dateStart!=null){
			dc.add(Restrictions.gt("operTime", dateStart));
		}
		if(dateEnd!=null){
			dc.add(Restrictions.lt("operTime", dateEnd));
		}
		dc.addOrder(Order.desc("operTime"));
		return PageQuery.queryForPagenation(smsBaseDao.getHibernateTemplate(), dc, index, size);
	}
	public String notParticipateAutoSwitch(String name){
		String msg=null;
		SmsConfig smsConfig=(SmsConfig)smsBaseDao.get(SmsConfig.class, name);
		if(smsConfig!=null){
			smsConfig.setFlag1(1);
			smsConfig.setFlag(1);
			smsBaseDao.update(smsConfig);
			msg="NOTPARTICIPATEAUTOSWITCHSUCCESS";
		}
		return msg;
	}

	public String selectchannelonesensitive() {
		// TODO Auto-generated method stub
		String msg=null;
		SmsAds smsAds=(SmsAds)smsBaseDao.get(SmsAds.class, 1);
		if(smsAds!=null){
			msg=smsAds.getAds();
		}
		return msg;
	}

	public String updateAds(String advertise) {
		// TODO Auto-generated method stub
		String msg=null;
		SmsAds smsAds = (SmsAds) smsBaseDao.get(SmsAds.class, 1);
		if(StringUtils.isNotEmpty(advertise)){
			smsAds.setAds(advertise);
		}
		 smsBaseDao.update(smsAds);
         msg = "SMSADS_UPDATED";
		return msg;
	}

	public NexmoResponse findByMessageId(String messageId) {
		// TODO Auto-generated method stub
		NexmoResponse nexmoResponse=(NexmoResponse)smsBaseDao.get(NexmoResponse.class, messageId);
		return nexmoResponse;
	}

	public void saveOrUpdate(NexmoResponse nexmoResponse) {
		// TODO Auto-generated method stub
		smsBaseDao.saveOrUpdate(nexmoResponse);
	}

	public void update(NexmoResponse nexmoResponse1) {
		// TODO Auto-generated method stub
		smsBaseDao.update(nexmoResponse1);
	}

	public void save(NexmoResponse nexmoResponse1) {
		// TODO Auto-generated method stub
		smsBaseDao.save(nexmoResponse1);
	}
	public List<SmsMessages> getSmsMessage(String product,String smsprovider, Date datestart, Date dateEnd) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=DetachedCriteria.forClass(SmsMessages.class);
		criteria.add(Restrictions.eq("product", product));
		criteria.add(Restrictions.eq("smsprovider", smsprovider));
		criteria.add(Restrictions.gt("createtime", datestart));
		criteria.add(Restrictions.lt("createtime", dateEnd));
		criteria.addOrder(Order.asc("createtime"));
		return smsBaseDao.findByCriteria(criteria);
	}

	public List<Integer> getUsableChanneList(String platformId) {
		// TODO Auto-generated method stub
		  DetachedCriteria dc = DetachedCriteria.forClass(SmsConfig.class);
		  dc.add(Restrictions.eq("platformIds", platformId));
		  dc.add(Restrictions.eq("flag", 0));
		  dc.add(Restrictions.eq("flag1", 0));
	      dc.setProjection(Projections.distinct(Projections.property("channel")));
	      return smsBaseDao.findByCriteria(dc);
	}
}
