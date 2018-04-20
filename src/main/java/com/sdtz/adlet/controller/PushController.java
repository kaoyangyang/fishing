package com.sdtz.adlet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/push")
public class PushController {

	//推送服务器的api
	public static final String PUSH_URL = "notification.do?action=send";


	@RequestMapping("/push.do")

	public void push( @RequestParam(value = "Id") String Id,
	                    @RequestParam(value = "title") String title,
	                    @RequestParam(value = "content") String content,
	                    @RequestParam(value = "age") String age,
	                    @RequestParam(value = "sex") String sex,
	                    @RequestParam(value = "job_id") String job_id,
	                    @RequestParam(value = "province") String province,
	                    @RequestParam(value = "city") String city,
	                    @RequestParam(value = "district") String district,
	                    @RequestParam(value = "town") String town,
	                    @RequestParam(value = "lng") String lng,
	                    @RequestParam(value = "lat") String lat) {

			Map<String, String> requestmap = new HashMap<String, String>();
			Map<String, String> urlmap = new HashMap<String, String>();
			//设置url参数
			long timestamp = System.currentTimeMillis();
			urlmap.put("action", "send");
//			urlmap.put("Id", Id);
//			urlmap.put("title", title);
//			urlmap.put("content", content);
//			urlmap.put("age", age);
//			urlmap.put("sex", sex);
//			urlmap.put("job_id", job_id);
//			urlmap.put("province", province);
//			urlmap.put("city", city);
//			urlmap.put("district", district);
//			urlmap.put("town", town);
//			urlmap.put("lng", lng);
//			urlmap.put("lat", lat);


			//请求api
			this.sendPost(PUSH_URL+"?",urlmap, requestmap );
			
		}


	public static String sendPost(String url, Map<String, String> urlParameters, Map<String, String> requestParameters) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();// 处理请求参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if (urlParameters.size() == 1) {
				for (String name : urlParameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(urlParameters.get(name),
									"UTF-8"));
				}
				params = sb.toString();
			} else {
				for (String name : urlParameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(urlParameters.get(name),
									"UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				if (temp_params.length() > 1) {
					params = temp_params.substring(0, temp_params.length() - 1);
				} else {

				}
			}
			// 创建URL对象
			url+=params;
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Charset", "UTF-8");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//httpConn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
			// 设置POST方式
			httpConn.setRequestMethod("POST");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setUseCaches(false);
			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			StringBuffer parameterBuffer = new StringBuffer();
			//Gson gson = new Gson();
//			// 发送请求参数
//			String s = gson.toJson(requestParameters);
//			System.out.println("qunima"+s);
//			out.write(s);
			if (requestParameters != null) {
	            Iterator iterator = requestParameters.keySet().iterator();
	            String key = null;
	            String value = null;
	            while (iterator.hasNext()) {
	                key = (String)iterator.next();
	                if (requestParameters.get(key) != null) {
	                    value = (String)requestParameters.get(key);
	                } else {
	                    value = "";
	                }
	                parameterBuffer.append(key).append("=").append(value);
	                if (iterator.hasNext()) {
	                    parameterBuffer.append("&");
	                }
	            }
	        }
			out.write(parameterBuffer.toString());
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

//	public static String sendPost(String url, Map<String, String> urlParameters, Map<String, String> requestParameters) {
//		String result = "";// 返回的结果
//		BufferedReader in = null;// 读取响应输入流
//		PrintWriter out = null;
//		StringBuffer sb = new StringBuffer();// 处理请求参数
//		String params = "";// 编码之后的参数
//		try {
//			// 编码请求参数
//			if (urlParameters.size() == 1) {
//				for (String name : urlParameters.keySet()) {
//					sb.append(name).append("=").append(
//							java.net.URLEncoder.encode(urlParameters.get(name),
//									"UTF-8"));
//				}
//				params = sb.toString();
//			} else {
//				for (String name : urlParameters.keySet()) {
//					sb.append(name).append("=").append(
//							java.net.URLEncoder.encode(urlParameters.get(name),
//									"UTF-8")).append("&");
//				}
//				String temp_params = sb.toString();
//				if (temp_params.length() > 1) {
//					params = temp_params.substring(0, temp_params.length() - 1);
//				} else {
//
//				}
//
//			}
//			// 创建URL对象
//			url+=params;
//			java.net.URL connURL = new java.net.URL(url);
//			// 打开URL连接
//			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
//					.openConnection();
//			// 设置通用属性
//
//			httpConn.setRequestProperty("Content-type", "application/json;charset=utf-8");
//
//			// 设置POST方式
//			httpConn.setDoInput(true);
//			httpConn.setDoOutput(true);
//			// 获取HttpURLConnection对象对应的输出流
//			out = new PrintWriter(httpConn.getOutputStream());
//			Gson gson = new Gson();
//			// 发送请求参数
//			String s = gson.toJson(requestParameters);
//			System.out.println("qunima"+s);
//			out.write(s);
//			// flush输出流的缓冲
//			out.flush();
//			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
//			in = new BufferedReader(new InputStreamReader(httpConn
//					.getInputStream(), "UTF-8"));
//			String line;
//			// 读取返回的内容
//			while ((line = in.readLine()) != null) {
//				result += line;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//				if (in != null) {
//					in.close();
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return result;
//	}

	public static String sendGet(String url, Map<String, String> parameters) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		StringBuffer sb = new StringBuffer();// 存储参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8"));
				}
				params = sb.toString();
			} else {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			String full_url = url + "?" + params;
			System.out.println(full_url);
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(full_url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Content-type", "application/json;charset=utf-8");
			// 建立实际的连接
			httpConn.connect();
			// 响应头部获取
			Map<String, List<String>> headers = httpConn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : headers.keySet()) {
				System.out.println(key + "\t：\t" + headers.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}


}
