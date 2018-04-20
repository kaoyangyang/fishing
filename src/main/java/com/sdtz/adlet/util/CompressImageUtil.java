package com.sdtz.adlet.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class CompressImageUtil {

	
	/**
	 * 根据设定的宽和高压缩图片
	 * @param file //源文件
	 * @param width //设定的压缩宽度
	 * @param height //设定的压缩高度 
	 */
	public static boolean compressByWH(CommonsMultipartFile file,String path){
		//定义压缩比例
		double scale=1;
		InputStream in=null;
		OutputStream out=null;
		try {
			//获取输入流
			in=file.getInputStream();
			//将输入流读入到图片缓存器中
			BufferedImage bi=ImageIO.read(in);
			//获取文件的原名称
			String filename=file.getOriginalFilename();
			//获取原图的宽和高
			double srcheight=bi.getHeight();
			double srcwidth=bi.getWidth();
			//System.out.println(srcwidth+":"+srcheight);
			//判断是否需要压缩
//			if(srcwidth<=width && srcheight<=height){
//				//原图的宽和高都小于设定的宽和高 则不需要压缩
//				scale=1;
//			}else if(srcwidth>width && srcheight<height){
//				//原图的宽度大于设定的宽度需要压缩,原图高度小于设定高度
//				scale=width/srcwidth;
//			}else if(srcwidth<width && srcheight>height){
//				//原图的高度大于设定的高度需要压缩,原图宽度小于设定宽度
//				scale=height/srcheight;
//			}else if(srcwidth>width && srcheight>height){
//				//原图的宽高都大于设定的宽高,则按照较小的比例进行压缩
//				if(width/srcwidth>=height/srcheight){
//					scale=height/srcheight;
//				}else{
//					scale=width/srcwidth;
//				}
//			}
//			//计算压缩后的图片宽和高
//			int scalewidth=(int) (srcwidth*scale);
//			int scaleheight=(int) (srcheight*scale);
			//System.out.println(scalewidth+":"+scaleheight);
			//定义一个image
			Image image=bi.getScaledInstance((int)srcwidth, (int)srcheight, Image.SCALE_SMOOTH);
			//创建一块画布
			BufferedImage tag=new BufferedImage((int)srcwidth, (int)srcheight,BufferedImage.TYPE_INT_RGB);
			//创建画笔
			Graphics g = tag.getGraphics();
			//设置颜色
	        g.setColor(Color.RED);
	        // 绘制处理后的图
	        g.drawImage(image, 0, 0, null); 
	        //关闭画笔
	        g.dispose();
	        //定义服务器文件
	        //System.out.println("文件目录"+path);
	        File File=new File(path);
	        //定义一个输出流
	        out=new FileOutputStream(File);
	        //输出流写出到缓存文件目录中
	        return ImageIO.write(tag, "JPEG", out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(in!=null&&out!=null){
					//System.out.println("压缩完毕，关闭输出流");
					in.close();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
