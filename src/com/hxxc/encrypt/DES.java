package com.hxxc.encrypt;


import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import net.sf.json.JSONObject;




public class DES{

	// 密钥
	public static String secretKey = "zghbwhszzyjyshxxjsyxgszz";
	// 向量
	public static String iv = "15743628";
	// 编码
	public static String encoding = "utf-8";

	/**
	 * CBC加密
	 * 
	 * @param data
	 *
	 * @return
	 * @throws Exception
	 */
	
	
	public DES(String channel){
		// 密钥
		 secretKey ="zghbwhszzyjyshxxjsyxgszz";
		// 向量
		iv = "15743628";
	}

	public static  String EncodeCBC(String data) {

		Key deskey = null;
		DESedeKeySpec spec;
		try {
			spec = new DESedeKeySpec(secretKey.getBytes(encoding));

			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);

			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(data.getBytes(encoding));
			return Base64.encode(encryptData);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * CBC 解密
	 *
	 * @param data
	 *            �����ַ���
	 * @return �����ַ���
	 * @throws Exception
	 */

	public static  String DecodeCBC(String data) {
		Key deskey = null;
		try {
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes(encoding));
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

			byte[] decryptData = cipher.doFinal(Base64.decode(data));

			return new String(decryptData, encoding);
		} catch (Exception e) {
        e.printStackTrace();
			return "";
		}
	}

	
	
	
	
	public static void main(String[] args) throws Exception {
/*		DES des= new DES("10000");
		System.out.println("123456="+des.EncodeCBC("123456"));*/
		DES des = new DES("10000");
		Map<String, Object> requestParam = new HashMap<String, Object>();
		requestParam.put("command", "getNewVersion");
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("name", "test");
		heads.put("pwd", "pwssword");		
		heads.put("version", "1.0.0.0");
		heads.put("channel", "10000");
		heads.put("client", "web");
		heads.put("clientid", "02100001");
		heads.put("businessType", "CaiPiaoHandlerImp");
		JSONObject jsonObject = JSONObject.fromObject(heads);
		String secretHead = des.EncodeCBC(jsonObject.toString());
		requestParam.put("Head", secretHead);
		System.out.println(requestParam);
		MD5 md5 = new MD5();
		String newstr ="zghbwhszzyjyshxxjsyxgszz"+secretHead+"15743628" ;
		System.out.println(md5.encrypt(newstr));
	}

}
