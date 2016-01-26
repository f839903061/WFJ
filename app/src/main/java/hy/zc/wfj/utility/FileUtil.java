package hy.zc.wfj.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public class FileUtil {


	/**
	 * 将Bitmap 图片保存到本地路径，并返回路径
	 * @param c
	 * @param fileName 文件名称
	 * @param bitmap 图片
	 * @return
	 */
	public static String saveFile(Context c, String fileName, Bitmap bitmap) {
		return saveFile(c, "", fileName, bitmap);
	}
	
	public static String saveFile(Context c, String filePath, String fileName, Bitmap bitmap) {
		byte[] bytes = bitmapToBytes(bitmap);
		return saveFile(c, filePath, fileName, bytes);
	}
	
	public static byte[] bitmapToBytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
	
	public static String saveFile(Context c, String filePath, String fileName, byte[] bytes) {
		String fileFullName = "";
		FileOutputStream fos = null;
		String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date());
		try {
			String suffix = "";
			if (filePath == null || filePath.trim().length() == 0) {
//				filePath = Environment.getExternalStorageDirectory() + "/SmartWFJ/" + dateFolder + "/";
				filePath = c.getCacheDir() + "/SmartWFJ/" + dateFolder + "/";
			}
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			File fullFile = new File(filePath, fileName + suffix);
			fileFullName = fullFile.getPath();
			fos = new FileOutputStream(new File(filePath, fileName + suffix));
			fos.write(bytes);
		} catch (Exception e) {
			fileFullName = "";
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					fileFullName = "";
				}
			}
		}
		return fileFullName;
	}

	/**
	 * 删除缓存
	 * @param pContext
	 */
	public static void deleteCache(Context pContext){
		try {
			File dir = pContext.getCacheDir();
			if (dir!=null&&dir.isDirectory()) {
				deleteDir(dir);
			}
		}catch (Exception e ) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹
	 * @param pDir
	 * @return
	 */
	public static boolean deleteDir(File pDir){

		if (pDir != null && pDir.isDirectory()) {
			String[] children = pDir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(pDir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return pDir.delete();
	}

	/**
	 * 返回文件夹大小
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {

		if (dir.exists()) {
			long result = 0;
			File[] fileList = dir.listFiles();
			for(int i = 0; i < fileList.length; i++) {
				// Recursive call if it's a directory
				if(fileList[i].isDirectory()) {
					result += getDirSize(fileList[i]);
				} else {
					// Sum the file size in bytes
					result += fileList[i].length();
				}
			}
			return result; // return the file size
		}
		return 0;
	}
}
