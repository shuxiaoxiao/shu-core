package com.shupro.core.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.shupro.core.utils.SystemUtil;


/**
 * 文件上传下载工具类
 * 
 * @author Administrator
 *
 */
public class FileUtil {

	/**
	 * 文件上传(上传文件存放于upload文件夹) <br>
	 * <input type="file" name="file">中的name不受限制
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * 	<form action="file/upload.do" method="post" enctype="multipart/form-data"> 
	 * 		选择文件 <input type="file" name="file"> <input type="submit" value="上传"> 
	 * 	</form>
	 */
	public static String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		return upload(request, response, "upload", "");
	}
	
	/**
	 * 文件上传 <br>
	 * <input type="file" name="file">中的name不受限制
	 * 
	 * @param request
	 * @param response
	 * @param savePath		本地保存文件夹位置
	 * @return
	 * @throws IOException
	 * 	<form action="file/upload.do" method="post" enctype="multipart/form-data"> 
	 * 		选择文件 <input type="file" name="file"> <input type="submit" value="上传"> 
	 * 	</form>
	 */
	public static String upload(HttpServletRequest request, HttpServletResponse response, String savePath) throws IOException {
		
		return upload(request, response, savePath, "");
	}
	
	/**
	 * 文件上传 <br>
	 * <input type="file" name="file">中的name不受限制
	 * 
	 * @param request
	 * @param response
	 * @param savePath		本地保存文件夹位置
	 * @param namePrefix	文件名前缀
	 * @return
	 * @throws IOException
	 * 	<form action="file/upload.do" method="post" enctype="multipart/form-data"> 
	 * 		选择文件 <input type="file" name="file"> <input type="submit" value="上传"> 
	 * 	</form>
	 */
	public static String upload(HttpServletRequest request, HttpServletResponse response, String savePath, String namePrefix) throws IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		String newFileName = "";
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile((String) iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					String path = request.getSession().getServletContext().getRealPath(savePath);
					newFileName = namePrefix + SystemUtil.getNewFilename(fileName);
					File targetFile = new File(path, newFileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					file.transferTo(targetFile);
				}
			}
		}
		return newFileName;
	}

	/**
	 * 文件下载 
	 * 
	 * @param path 文件全路径
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String download(String path, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int index = path.lastIndexOf("/") + 1;
		String fileName = path.substring(index);
		// String savePath = path.substring(0,index);

		response.setCharacterEncoding("utf-8");
		response.setContentType(request.getSession().getServletContext().getMimeType(fileName));
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		// response.setHeader("Content-Disposition", "attachment;" +
		// encodeFileName(request, fileName));

		// String path =
		// request.getSession().getServletContext().getRealPath(savePath);
		InputStream inputStream = new FileInputStream(new File(path.replace("%20", " ")));

		OutputStream os = response.getOutputStream();
		byte[] b = new byte[2048];
		int length;
		while ((length = inputStream.read(b)) > 0) {
			os.write(b, 0, length);
		}
		// 这里主要关闭。
		os.close();
		inputStream.close();

		return null;
	}
	

//	public static String encodeFileName(HttpServletRequest request, String fileName) {
//
//		String userAgent = request.getHeader("User-Agent");
//		String rtn = "";
//		try {
//			String new_filename = URLEncoder.encode(fileName, "UTF8");
//			// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
//			rtn = "filename=" + new_filename;
//			if (userAgent != null) {
//				userAgent = userAgent.toLowerCase();
//				// IE浏览器，只能采用URLEncoder编码
//				if (userAgent.indexOf("msie") != -1) {
//					rtn = "filename=" + new_filename;
//				}
//				// Opera浏览器只能采用filename*
//				else if (userAgent.indexOf("opera") != -1) {
//					rtn = "filename*=UTF-8''" + new_filename;
//				}
//				// Safari浏览器，只能采用ISO编码的中文输出
//				else if (userAgent.indexOf("safari") != -1) {
//					rtn = "filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1");
//				}
//				// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
//				else if (userAgent.indexOf("applewebkit") != -1) {
//					new_filename = MimeUtility.encodeText(fileName, "UTF8", "B");
//					rtn = "filename=" + new_filename;
//
//				}
//				// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
//				else if (userAgent.indexOf("mozilla") != -1) {
//					rtn = "filename*=UTF-8''" + new_filename;
//				}
//			}
//
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		if (agent.contains("Firefox")) { // 火狐浏览器
//			filename = "=?UTF-8?B?"
//					+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
//					+ "?=";
//			filename = filename.replaceAll("\r\n", "");
//		} else { // IE及其他浏览器
//			filename = URLEncoder.encode(filename, "utf-8");
//			filename = filename.replace("+"," ");
//		}
//		return filename;
//		return rtn;
//	}

}
