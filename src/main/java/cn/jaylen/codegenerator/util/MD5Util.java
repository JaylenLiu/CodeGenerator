package cn.jaylen.codegenerator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @author ljl
 * @create 2018-07-02 9:59
 * @desc md5加密工具
 */
public class MD5Util {
	
	protected static final Logger logger = LoggerFactory.getLogger(MD5Util.class);
	
	public static String getMD5_16(String plainText) {
		String md5_16bit = null;
		try {
			md5_16bit = getMD5_32(plainText).substring(8, 24);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return md5_16bit;
	}
	
	public static String getMD5_32(String plainText) {
		String md5_32bit = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			md5_32bit = buf.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return md5_32bit;
	}
}
