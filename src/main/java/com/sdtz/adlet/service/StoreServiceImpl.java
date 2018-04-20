
package com.sdtz.adlet.service; 



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sdtz.adlet.dao.StoreDao;
import com.sdtz.adlet.entity.Store;
import com.sdtz.adlet.util.AdResult;
import com.sdtz.adlet.util.CompressImageUtil;


@Service("storeService")
public class StoreServiceImpl implements StoreService {
	@Resource
	private StoreDao storeDao;

	public AdResult createStore(Store store) {
		AdResult result=new AdResult();
		//调用创建我的店的接口返回一个结果码
		int resultcode=storeDao.createStore(store);
		if(resultcode>0){
			//创建成功
			result.setMsg("创建成功");
			result.setStatus(1);
		}else{
			//创建失败
			result.setMsg("创建失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult updateStore(Store store,HttpServletRequest request,CommonsMultipartFile[] files) {
		AdResult result=new AdResult();
		/*
		 * 压缩图片业务 
		 */
		//多张店面的展示图片组合成的字符串
		String lastPhotos=store.getPhotos();
		System.out.println(lastPhotos);
		//获取项目的根目录
		String projectPath=request.getSession().getServletContext().getRealPath(File.separator);
		//是否上传了文件
		if(files.length>0){
			//上传了文件,循环遍历文件
			for(int i=0;i<files.length;i++){
				//定义将要存储的图片的名称：采用用户的id号和当前系统毫秒时间组合
				Date now=new Date();
				String dbPath="upload"+File.separator+"storephotos"+File.separator+store.getStoreid()+now.getTime();
				//压缩并上传图片
				CompressImageUtil.compressByWH(files[i],projectPath+dbPath);
				//表示最后一张图片
				lastPhotos=lastPhotos+dbPath+",";
			}
		}else{
			//未上传文件
		}
		//System.out.println(filespath);
		store.setPhotos(lastPhotos);
		//修改我的店的信息
		int resultcode=storeDao.updateStore(store);
		if(resultcode>0){
			//修改成功
			result.setMsg("修改成功");
			result.setStatus(1);
		}else{
			//修改失败
			result.setMsg("修改失败");
			result.setStatus(0);
		}
		return result;
	}
	
	
	public AdResult updateStore(Store store,HttpServletRequest request) {
		AdResult result=new AdResult();
		String lastPhotos=store.getPhotos();
		System.out.println(lastPhotos);
		//将删除的图片进行删除操作
		String projectPath=request.getSession().getServletContext().getRealPath(File.separator);
		//TODO
		String[] deletePhotos=store.getDeletePhotos().split(",");
		System.out.println(store.getDeletePhotos());
		//删除图片
		for(int i=0;i<deletePhotos.length;i++){
			File file=new File(projectPath+deletePhotos[i]);
			if(file.exists()){
				file.delete();
			}
		}
		int resultcode=storeDao.updateStore(store);
		if(resultcode>0){
			result.setMsg("修改成功");
			result.setStatus(1);
		}else{
			result.setMsg("修改失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult getStore(int storeid) {
		AdResult result=new AdResult();
		//查找我的店
		Store store=storeDao.getStore(storeid);
		if(store!=null){
			//查找成功
			result.setData(store);
			result.setMsg("查找成功");
			result.setStatus(1);
		}else{
			//查找失败
			result.setMsg("查找失败");
			result.setStatus(0);
		}
		return result;
	}

	@Override
	public AdResult updateStoreIcon(Store store,HttpServletRequest request,CommonsMultipartFile file) {
		AdResult result=new AdResult();
		//我的事业的背景照片在数据库中的存放地址
		String filepath="upload"+File.separator+"storeicon"+File.separator+store.getStoreid();
		//获取项目的根目录
		String projectPath=request.getSession().getServletContext().getRealPath(File.separator);
		//如果有图片
		if(!file.isEmpty()){
			//压缩并上传图片
			CompressImageUtil.compressByWH(file,projectPath+filepath);
		}
		//将数据库地址设置到我的事业对象中
		store.setIcon(filepath);
		//System.out.println(store.toString());
		//进行修改操作
		int resultcode=storeDao.updateStoreIcon(store);
		if(resultcode>0){
			result.setMsg("更换成功");
			result.setStatus(1);
		}else{
			result.setMsg("更换失败");
			result.setStatus(0);
		}
		return result;
	}

}
 