//package com.sn.sms.aspect;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.sn.sms.model.Constants;
//import com.sn.sms.model.sms.SmsAccount;
//import com.sn.sms.model.sms.SmsConfigLog;
//import com.sn.sms.service.interfaces.SmsConfigLogService;
//import com.sn.sms.service.interfaces.SmsService;
//import com.sn.sms.utils.DateUtils;
//import net.sf.json.JSONArray;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Map;
//import java.util.Properties;
//
//public class SmsConfigLogger {
//	@Resource
//	private SmsConfigLogService smsConfigLogService;
//	@Autowired
//	private SmsService smsService;
//	private static Properties prop=null;
//	static{
//		try{
//			prop=new Properties();
//			InputStream in=SmsConfigLogger.class.getClassLoader().getResourceAsStream("conversion.properties");
//			prop.load(in);
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//	}
//	public Object record(ProceedingJoinPoint pjp){
//		SmsConfigLog smsConfigLog=new SmsConfigLog();
//		try{
//			ActionContext ac=ActionContext.getContext();
//			if(ac!=null){
//				Map<String,Object> session=ac.getSession();
//				if(session!=null){
//					SmsAccount smsAccoun=(SmsAccount)session.get(Constants.SESSION_CUSTOMERID);
//					if(smsAccoun!=null){
//						smsConfigLog.setOperator(smsAccoun.getLoginname());
//						smsConfigLog.setPlatformIds(smsAccoun.getPlatformIds());
//					}
//				}
//			}
//			Object[] params=pjp.getArgs();
//			smsConfigLog.setOperParams(JSONArray.fromObject(params).toString());
//			String mname=pjp.getSignature().getName();
//			if("login".equals(mname)){
//				if(params.length>0){
//					SmsAccount smsAccoun=smsService.getSmsAccount(params[0].toString(),params[1].toString());
//					if(smsAccoun!=null){
//						smsConfigLog.setOperator(smsAccoun.getLoginname());
//						smsConfigLog.setPlatformIds(smsAccoun.getPlatformIds());
//					}else{
//						smsConfigLog.setOperator(params[0].toString());
//						smsConfigLog.setPlatformIds("");
//					}
//				}
//			}
//			String convertName=prop.getProperty(mname);
//			smsConfigLog.setOperName(convertName);
//
//			Object ret=pjp.proceed();
//			smsConfigLog.setOpeResult("成功");
//			if(ret!=null && prop.getProperty(ret.toString())!=null){
//					smsConfigLog.setResultMsg(prop.getProperty(ret.toString()));
//			}else{
//				smsConfigLog.setResultMsg(null);
//			}
//			return ret;
//		}catch(Throwable e){
//			smsConfigLog.setOpeResult("失败");
//			smsConfigLog.setResultMsg(e.getMessage());
//		}finally{
//			smsConfigLog.setOperTime(DateUtils.parseDateForStandard());
//			smsConfigLogService.saveEntity(smsConfigLog);
//		}
//		return null;
//	}
//}
