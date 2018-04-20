
package com.sdtz.adlet.service;

import com.sdtz.adlet.controller.PushController;
import com.sdtz.adlet.dao.AdDao;
import com.sdtz.adlet.dao.UserDao;
import com.sdtz.adlet.entity.*;
import com.sdtz.adlet.util.AdResult;
import com.sdtz.adlet.util.CompressImageUtil;
import com.sdtz.adlet.util.EncodeUtil;
import com.sdtz.adlet.util.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	@Resource
	private UserDao userDao;
	@Resource
	private CodeService codeService;
	@Resource
	private StoreService sotreService;
	@Resource
	private AdDao addao;

	@Override
	public User login(HashMap<String, Object> params) {
		User user = userDao.login(params);
		return user;
	}

	@Override
	@Transactional
	public AdResult register(String username,String phone, String login_password, Integer code) throws Exception {
		System.out.println(username+":"+phone+":"+login_password);
		AdResult result = new AdResult();
		// 当用户输入的电话号码不为空时再判断是否已经注册过
		if (!phone.equals("")) {
			User user = userDao.findByPhone(phone);
			if (user != null) {
				result.setStatus(0);
				result.setMsg("您已注册过,请直接登录!");
				return result;
			}
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("phone", phone);
		params.put("code", code);
		// 如果还没进行长连接注册，并且未注册过
		if (username==null) {//123456时临测试设置的验证码
			// 直接进行注册
			params.put("password", login_password);
			User addUser = new User();
			addUser.setLogin_password(EncodeUtil.md5(login_password));
			addUser.setPhone(phone);
			Date created_time = new Date();
			addUser.setCreatedDate(created_time);
			userDao.save(addUser);
			result.setStatus(1);
			result.setMsg("注册成功!");
			return result;
		} else {// add by yedong
			Code getCode = codeService.findByPhone(params);
			if (getCode == null) {
				result.setStatus(0);
				result.setMsg("验证码错误,请重试!");
				return result;
			} else {
				int now = (int) (System.currentTimeMillis() / 1000);
				int a = getCode.getEndtime() - now;
				Integer code2 = getCode.getCode();
				if (code2.equals(code) && (a > 0)) {
					User addUser = new User();
					addUser.setLogin_password(EncodeUtil.md5(login_password));
					addUser.setPhone(phone);
					addUser.setUsername(username);
					
//					Date addtime = new Date();
//					addUser.setCreatedDate(addtime);
					//int resultcode = userDao.save(addUser);
					int resultcode = userDao.saveUser(addUser);
					if (resultcode > 0) {
						result.setStatus(1);
						result.setMsg("注册成功!");
						return result;
					} else {
						result.setStatus(0);
						result.setMsg("注册失败!");
						return result;
					}
					// 同时初始化我的店铺
					// Store store = new Store();
					// store.setId(addUser.getId());
					// store.setTel(phone);
					// store.setName(phone);
					// sotreService.createStore(store);
				} else if (a < 0) {
					result.setStatus(0);
					result.setMsg("验证码超时,请重新发送!");
					return result;
				}
				result.setStatus(0);
				result.setMsg("验证码错误,请重试!");
				return result;
			}
		}

	}

	@Override
	public AdResult getCode(String phone) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);
		AdResult result = new AdResult();
		User user = userDao.findByPhone(phone);
		if (user != null) {
			result.setStatus(0);
			result.setMsg("您已注册过,请直接登录!");
			return result;
		}
		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");

		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);

		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

		NameValuePair[] data = { // 提交短信
				new NameValuePair("account", "cf_18753916953"), // 查看用户名是登录用户中心->验证码短信->产品总览->APIID
				new NameValuePair("password", "db68a0043ba3d796d2ad17d63acd832b"), // 查看密码请登录用户中心->验证码短信->产品总览->APIKEY
				// new NameValuePair("password",
				// util.StringUtil.MD5Encode("密码")),
				new NameValuePair("mobile", phone), new NameValuePair("content", content), };
		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			if ("2".equals(code)) {
				Code yzcode = new Code();
				yzcode.setCode(mobile_code);
				yzcode.setPhone(phone);
				yzcode.setEndtime((int) (System.currentTimeMillis() / 1000 + 300));
				codeService.save(yzcode);
				System.out.println("短信提交成功");
				result.setStatus(1);
				result.setMsg("发送成功!");

			} else {
				result.setStatus(0);
				result.setMsg("发送失败,请重试!");
			}

		} catch (HttpException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)//开启事务
	public AdResult login(String phone, String login_password,String username) {
		System.out.println("别处登录的用户名："+username);
		AdResult result = new AdResult();
		User findUser = userDao.findByPhone(phone);
		if (findUser == null) {
			result.setStatus(0);
			result.setMsg("用户不存在!");
			return result;
		} else {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("phone", phone);
			try {
				params.put("login_password", EncodeUtil.md5(login_password));
			} catch (Exception e) {

				e.printStackTrace();
			}
			User user = this.login(params);
			if (user != null) {
				result.setStatus(1);
				result.setMsg("登陆成功!");
				result.setData(user);
				//判断用户是否本次长连接用户的username和数据库中的一致
				if(!username.equals(user.getUsername())){
					//给被迫下线的用户一个推送通知
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("username", user.getUsername());
					Map<String, String> urlmap = new HashMap<String, String>();
			    	urlmap.put("action", "sendOffLineMessage");
					//PushController.sendPost("http://127.0.0.1:8080/androidpnserver/notification.do?", urlmap, map);
					PushController.sendPost("http://127.0.0.1:8080/notification.do?", urlmap, map);
					//修改用户数据库中的长连接username为本次的并将之前登录记录删除
					userDao.deleteOldLoginRecord(user.getUsername());
					//并将老的用户信息保存到新的连接账号记录中
					System.out.println(user);
					user.setUsername(username);
					userDao.saveOldLoginRecord(user);
				}
			} else {
				result.setStatus(0);
				result.setMsg("用户名或密码错误!");
			}
			return result;
		}

	}

	

	@Override
	public AdResult getMyAds(int user_id, int pageIndex, int pageSize) {
		AdResult result = new AdResult();
		// 定义一个map用于存放请求参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("pageIndex", (pageIndex - 1) * pageSize);
		params.put("pageSize", pageSize);
		// 调用dao接口获取广告
		List<Ad> myads = userDao.getMyAds(params);
		// 如果获取的数据不为空
		if (myads != null) {
			result.setStatus(1);
			result.setData(myads);
			result.setMsg("获取成功");
		} else {
			result.setMsg("没有数据");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult deleteAd(int id) {
		// String path=this.getClass().getResource(File.separator).toString();
		// String projectPath=path.split("WEB-INF")[0];
		// System.out.println(projectPath);
		AdResult result = new AdResult();
		/*
		 * 删除数据库中的记录时同事删除其对应的图片,利用file类操作文件删除
		 * 思路：删除之前先拿到广告对象本身，获取广告的图片地址信息，再拿到项目所在的根目录就可以锁定文件所在位置 进而将其删除 步骤
		 * 1、先获取到某条广告 2、将数据库中存的广告对象删除 3、将该条广告的所有图片的数据库地址信息保存至一个数组中存储 4、拿到项目根目录
		 * 5、逐个删除图片
		 */
		// 1获取广告本身
		Ad ad = addao.getAdById(id);
		// 2、删除数据库中的广告对象
		int resultcode = userDao.deleteAd(id);
		if (resultcode > 0) {
			String filePaths = null;
			String[] thrumFilePaths = null;
			// 3、创建保存图片地数组并初始化
			if (ad.getPhotos() != null) {
				filePaths = ad.getPhotos();//将其转成集合
	
				// 4、项目根目录D:\Users\Administrator\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\adlet\
				// String
				// projectPath="D:"+File.separator+"Users"+File.separator+"Administrator"+File.separator+"workspace"+File.separator+
				// ".metadata"+File.separator+".plugins"+File.separator+"org.eclipse.wst.server.core"+File.separator+
				// "tmp1"+File.separator+"wtpwebapps"+File.separator+"adlet"+File.separator+"";
				String path = this.getClass().getResource(File.separator).getPath().toString();
				String projectPath = path.split("WEB-INF")[0];
				// System.out.println(projectPath);
				// 5、循环删除图片
				//TODO
//				for (int i = 0; i < filePaths.size(); i++) {
//					// 创建文件
//					File file = new File(projectPath + filePaths.get(i));
//					if (file.exists()) {
//						file.delete();
//					}
//				}
			}
			result.setMsg("删除成功");
			result.setStatus(1);
		} else {
			result.setMsg("删除失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult saveUserLocation(UserLocation userLocation) {
		AdResult result = new AdResult();
		// 获取当前系统时间
		userLocation.setTime(new Date());
		int resultcode = userDao.saveUserLocation(userLocation);
		if (resultcode > 0) {
			result.setStatus(1);
			result.setMsg("保存成功");
		} else {
			result.setStatus(0);
			result.setMsg("保存失败");
		}
		return result;
	}

	@Override
	public AdResult getUserInfo(int id) {
		AdResult result = new AdResult();
		User userInfo = userDao.getUserInfo(id);
		if (userInfo != null) {
			result.setData(userInfo);
			result.setMsg("获取用户详细信息成功");
			result.setStatus(1);
		} else {
			result.setMsg("获取失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult getBalance(int id) {
		AdResult result = new AdResult();
		User user = userDao.getBalance(id);
		if (user != null) {
			result.setData(user);
			result.setMsg("获取用户余额成功");
			result.setStatus(1);
		} else {
			result.setMsg("获取失败");
			result.setStatus(0);
		}
		return result;
	}
	
	
	@Override
	public AdResult update(User user) {
		AdResult result = new AdResult();
		int resultn = userDao.updatenoicon(user);
		if (resultn > 0) {
			result.setMsg("修改成功!");
			result.setStatus(1);
		} else {
			result.setMsg("修改成失败!");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult update(User user, HttpServletRequest request, CommonsMultipartFile file) {
		// 头像的压缩后的在当前项目的存储地址
		String thrumIcon = "";
		// 获取项目的根目录
		String basePath = request.getSession().getServletContext().getRealPath(File.separator);
		// 如果文件不为空
		if (!file.isEmpty()) {
			// 定义头像图片相对项目根目录的存放的地址
			thrumIcon = "upload" + File.separator + "icon" + File.separator + user.getId() + ".png";
			// 压缩并上传
			CompressImageUtil.compressByWH(file, basePath + thrumIcon);
			
		}
		// 将压缩后的地址字符串存入到user对象中
		user.setIcon(thrumIcon);
		// 创建结果对象
		AdResult result = new AdResult();
		// 调用dao接口写入到数据库
		int i = userDao.update(user);
		if (i > 0) {
			result.setMsg("修改成功");
			result.setStatus(1);
		} else {
			result.setMsg("修改失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult getJobCode() {
		AdResult result=new AdResult();
		List<JobCode> jobCodes=userDao.getJobCode();
		if(jobCodes!=null){
			result.setData(jobCodes);
			result.setMsg("获取行业职业");
			result.setStatus(1);
		}else{
			result.setMsg("获取行业职业失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult updateMyType(User user) {
		AdResult adResult=new AdResult();
		int resultCode=userDao.updateMyType(user);
		if(resultCode>0){
			adResult.setMsg("修改成功");
			adResult.setStatus(1);
		}else{
			adResult.setMsg("修改失败");
			adResult.setStatus(0);
		}
		return adResult;
	}

	@Override
	public String getMyType(int id) {
		List<User> users=userDao.getMyType(id);
		String myType="";
		if(users.size()>0){
			myType=users.get(0).getMyType();
		}
		return myType;
	}

	/**
	 * 根据圈点获取在圈内的用户集合
	 * @param lon
	 * @param lat
	 * @param radius
	 * @return
	 */
	public AdResult findAll(double lon,double lat,int radius){
		AdResult adResult=new AdResult();
		List<User> users=userDao.findAllLocations();
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()){
			User user = iterator.next();
			Double lng = user.getLng();
			Double lat1 = user.getLat();
			double getDistance = MapUtils.GetDistance(lon,lat,lng,lat1);

			if(getDistance>radius){
				iterator.remove();
			}
		}
		adResult.setData(users);
		return adResult;
	}

	@Override
	public AdResult findAllAround(double lon, double lat, int radius) {
		AdResult adResult=new AdResult();
		List<UserLocation> UserLocations=userDao.findAllArountLocations();
		Iterator<UserLocation> iterator = UserLocations.iterator();
		while (iterator.hasNext()){
			UserLocation userLocation = iterator.next();
			Double lng = userLocation.getLng();
			Double lat1 = userLocation.getLat();
			double getDistance = MapUtils.GetDistance(lon,lat,lng,lat1);

			if(getDistance>radius){
				iterator.remove();
			}
		}
		adResult.setData(UserLocations);
		return adResult;
	}

	@Override
	public AdResult pushAdTop(Integer id) {
		AdResult adResult=new AdResult();
		int resultcode = userDao.pushAdTop(id);
		if(resultcode>0){
			adResult.setMsg("置顶成功");
			adResult.setStatus(1);
		}else{
			adResult.setMsg("置顶失败");
			adResult.setStatus(0);
		}
		return adResult;
	}

	@Override
	public AdResult getUserInfoByPhone(String phone) {
		AdResult adResult=new AdResult();
		User user=userDao.getUserInfoByPhone(phone);
		if(user!=null){
			adResult.setData(user);
			adResult.setMsg("获取成功");
			adResult.setStatus(1);
		}else {
			adResult.setMsg("获取失败");
			adResult.setStatus(0);
		}
		return adResult;
	}

	
	

}
