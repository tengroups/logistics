package com.logistics.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jbarcode.JBarcode;
import org.jbarcode.JBarcodeFactory;

import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.EAN8Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.EAN8TextPainter;
import org.jbarcode.paint.TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

public class BarcodeHelper {
	/**
	 * 生成订单条形码
	 * 
	 * @param filePath
	 *            商品条形码图片存放路径：C://barcode//images//
	 * 
	 * @param barCode
	 *            商品条形码：13位
	 * @param imgFormat
	 *            图片格式
	 * 
	 * @return 图片存放路径+图片名称+图片文件类型
	 */
	// 条形码EAN13长度
	public static String createBarCode(String savePath, String jbarCode,
			String imgFormat) {

		// 校验全部省略……
		// if(StringUtils.isNotEmpty(savePath)){
		//

		// return null;
		// }
		// if(StringUtils.isNotEmpty(jbarCode)){
		// return null;
		// }
		// if(StringUtils.isNotEmpty

		// (imgFormat)){
		// return null;
		// }
		// if( jbarCode.length()!=13){
		// return null;
		// }

		try {

			BufferedImage bi = null;

			int len = jbarCode.length();

			// 实例化JBarcode
			// 这里三个参数，必要填写
			JBarcode jbarcode13 = new JBarcode(EAN13Encoder.getInstance(),
					WidthCodedPainter.getInstance(),
					EAN13TextPainter.getInstance());

			// 获取到前12位
			String barCode = jbarCode.substring(0, len - 1);

			// 获取到校验位
			String code = jbarCode.substring(len - 1, len);
			String checkCode = jbarcode13.calcCheckSum(barCode);

			if (!code.equals(checkCode)) {
				return "EN-13 条形码最后一位校验码 不对，应该是： " + checkCode;
			}

			/*
			 * 最重要的是这里的设置，如果明白了这里的设置就没有问题 如果是默认设置， 那么设置就是生成一般的条形码 如果不是默认
			 * 设置，那么就可以根据自己需要设置
			 */

			// 尺寸，面积，大小
			jbarcode13.setXDimension(Double.valueOf(0.8).doubleValue());
			// 条形码高度
			jbarcode13.setBarHeight(Double.valueOf(30).doubleValue());
			// 宽度率
			jbarcode13.setWideRatio(Double.valueOf(20).doubleValue());
			// 是否校验最后一位，默认是false
			jbarcode13.setShowCheckDigit(false);

			// 生成二维码
			bi = jbarcode13.createBarcode(barCode);

			// 定义图片名称
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String imgName = sdf.format(new Date()) + "_" + jbarCode;

			// 保存二维码图片

			FileOutputStream fileOutputStream = null;
			String imgPath = savePath + imgName + "." + imgFormat;

			fileOutputStream = new FileOutputStream(imgPath);
			ImageUtil.encodeAndWrite(bi, imgFormat, fileOutputStream, 96, 96);
			fileOutputStream.close();

			// 返回路径
			return imgPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 条形码EAN8长度
	public static String createBarCode8(String savePath, String jbarCode,
			String imgFormat) {

		try {

			BufferedImage bi = null;

			JBarcode localJBarcode = new JBarcode(EAN8Encoder.getInstance(),
					WidthCodedPainter.getInstance(),
					EAN8TextPainter.getInstance());
			// 生成二维码
			bi = localJBarcode.createBarcode(jbarCode);

			// 定义图片名称
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String imgName = sdf.format(new Date()) + "_" + jbarCode;

			// 保存二维码图片

			FileOutputStream fileOutputStream = null;
			String imgPath = savePath + imgName + "." + imgFormat;

			fileOutputStream = new FileOutputStream(imgPath);
			ImageUtil.encodeAndWrite(bi, imgFormat, fileOutputStream, 96, 96);
			fileOutputStream.close();

			// 返回路径
			return imgPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static final int BARCODE_HEIGHT = 15;
	private static final String FONT_FAMILY = "consola";
	private static final int FONT_SIZE = 14;

	public static JBarcode getJBarcode() {
		JBarcode jbc = null;
		if (jbc == null) {
			jbc = JBarcodeFactory.getInstance().createCode128();
			jbc.setTextPainter(CustomTextPainter.getInstance());
			jbc.setBarHeight(BARCODE_HEIGHT);
		}
		return jbc;
	}

	/**
	 * 自定义的 TextPainter， 允许定义字体，大小等等。
	 */
	public static class CustomTextPainter implements TextPainter {
		public static CustomTextPainter instance = new CustomTextPainter();

		public static CustomTextPainter getInstance() {
			return instance;
		}

		@Override
		public void paintText(BufferedImage barCodeImage, String text, int width) {
			Graphics g2d = barCodeImage.getGraphics();

			Font font = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE * width);
			g2d.setFont(font);
			FontMetrics fm = g2d.getFontMetrics();
			int height = fm.getHeight();
			int center = (barCodeImage.getWidth() - fm.stringWidth(text)) / 2;

			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, barCodeImage.getWidth(),
					barCodeImage.getHeight() * 1 / 20);
			g2d.fillRect(0, barCodeImage.getHeight() - (height * 9 / 10),
					barCodeImage.getWidth(), (height * 9 / 10));
			g2d.setColor(Color.BLACK);
			g2d.drawString(text, center, barCodeImage.getHeight()
					- (height / 10) - 2);
		}
	}

	// 生成15位条形码方法
	public static String createBarCode15(String savePath, String jbarCode,
			String imgFormat) {

		try {

			BufferedImage bi = null;

			JBarcode localJBarcode = getJBarcode();
			// 生成二维码
			bi = localJBarcode.createBarcode(jbarCode);

			// 定义图片名称
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String imgName = sdf.format(new Date()) + "_" + jbarCode;

			// 保存二维码图片

			FileOutputStream fileOutputStream = null;
			String imgPath = savePath + imgName + "." + imgFormat;
			 try {
	                try {
	                    savePath = URLDecoder.decode(savePath, "UTF-8");
	                } catch (UnsupportedEncodingException uee) {
	                    uee.printStackTrace();
	                    //savePath = "C://barcode//images//";
	                }
	                File dirFile = new File(savePath);
	 
	                if (!dirFile.exists()) {
	                    dirFile.mkdirs();
	                    System.out.println("目录创建成功！");
	                }
	 
	                fileOutputStream = new FileOutputStream(imgPath);
	            } catch (Exception e) {
	                e.printStackTrace();
	                return null;
	            }
			//fileOutputStream = new FileOutputStream(imgPath);
			ImageUtil.encodeAndWrite(bi, imgFormat, fileOutputStream, 96, 96);
			fileOutputStream.close();

			// 返回路径
			return imgPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String createBarCode15(HttpServletRequest request,
			String path, String jbarCode) throws IOException {
		// 自动生成路径方法
		// 对请求进行utf-8编码
		request.setCharacterEncoding("UTF-8");
		// 通过当前日期生成对应的文件夹保存条形码图片
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String uploadSaveFolder = format.format(new Date());
		// 获取服务器路径
		// 这个路径相对当前应用的目录
		String uploadPath = setrootPath(request, path + "\\" + uploadSaveFolder);
		// 打印输出路径
		System.out.println(uploadPath);

		// 存入数据库的文件路径
		String uploadFile2Database = path + "\\" + uploadSaveFolder + "\\";

		return createBarCode15(uploadPath, jbarCode, ImageUtil.JPEG);
	}

	// 自动生成带根目录的条形码方法
	/**
	 * 生成订单条形码
	 * 
	 * @param request
	 *            前端请求，主要为获取服务器的根目录
	 * 
	 * @param path
	 *            自定义条形码保存在服务器根目录的哪个文件夹下
	 * @param jbarCode
	 *            订单条形码：13位
	 * 
	 * @return 图片存放路径+图片名称+图片文件类型
	 * @throws IOException
	 */
	// 返回图片路径 存入数据库
	public static String createBarCode13(HttpServletRequest request,
			String path, String jbarCode) throws IOException {
		// 自动生成路径方法
		// 对请求进行utf-8编码
		request.setCharacterEncoding("UTF-8");
		// 通过当前日期生成对应的文件夹保存条形码图片
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String uploadSaveFolder = format.format(new Date());
		// 获取服务器路径
		// 这个路径相对当前应用的目录
		String uploadPath = setrootPath(request, path + "\\" + uploadSaveFolder);
		// 打印输出路径
		System.out.println(uploadPath);

		// 存入数据库的文件路径
		String uploadFile2Database = path + "\\" + uploadSaveFolder + "\\";

		return createBarCode(uploadPath, jbarCode, ImageUtil.JPEG);
	}

	/**
	 * 生成订单条形码
	 * 
	 * @param request
	 *            前端请求，主要为获取服务器的根目录
	 * 
	 * @param path
	 *            自定义条形码保存在服务器根目录的哪个文件夹下
	 * @param jbarCode
	 *            订单条形码：8位
	 * 
	 * @return 图片存放路径+图片名称+图片文件类型
	 * @throws IOException
	 */
	public static String createBarCode8(HttpServletRequest request,
			String path, String jbarCode) throws IOException {
		// 自动生成路径方法
		// 对请求进行utf-8编码
		request.setCharacterEncoding("UTF-8");
		// 通过当前日期生成对应的文件夹保存条形码图片
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String uploadSaveFolder = format.format(new Date());
		// 获取服务器路径
		// 这个路径相对当前应用的目录
		String uploadPath = setrootPath(request, path + "\\" + uploadSaveFolder);
		// 打印输出路径
		System.out.println(uploadPath);

		// 存入数据库的文件路径
		String uploadFile2Database = path + "\\" + uploadSaveFolder + "\\";

		return createBarCode8(uploadPath, jbarCode, ImageUtil.JPEG);
	}

	// 初始化文件上传路径
	private static String setrootPath(HttpServletRequest req, String mypath) {
		// 获得服务器的名字
		String serverName = req.getServerName();
		// 取得互联网程序的绝对地址
		String realPath = req.getRealPath(serverName);
		realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
		String rootPath = realPath + "\\" + mypath + "\\";

		return rootPath;
	}

}
