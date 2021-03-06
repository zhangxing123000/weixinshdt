package com.weixin.wxutil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.weixin.http.AesException;

/**
* 类名: SignUtil </br>
* 描述: 检验signature 工具类 </br> 
 */
public class SignUtil {
    
    // 与接口配置信息中的Token要一致
    private static String token = "mscpwx1";

    /**
    * 方法名：checkSignature</br>
    * 详述：验证签名</br>
    * 开发人员：王志宁</br>
    * 创建时间：20170721  </br>
    * @param signature
    * @param timestamp
    * @param nonce
    * @return
    * @throws
     */
    public static boolean checkSignature123(String signature, String timestamp,String nonce,String  echostr)throws AesException {
        // 1.将token、timestamp、nonce三个参数进行字典序排序

    	  System.out.print("\n checkSignature signature timestamp  nonce "+signature+"|"+timestamp+"|"+nonce);
      String tmpStr="";
    	  try {
  			String[] array = new String[] { token, timestamp, nonce, echostr };
  			StringBuffer sb = new StringBuffer();
  			// 字符串排序
  			Arrays.sort(array);
  			for (int i = 0; i < 4; i++) {
  				sb.append(array[i]);
  			}
  			String str = sb.toString();
  			// SHA1签名生成
  			MessageDigest md = MessageDigest.getInstance("SHA-1");
  			md.update(str.getBytes());
  			byte[] digest = md.digest();

  			StringBuffer hexstr = new StringBuffer();
  			String shaHex = "";
  			for (int i = 0; i < digest.length; i++) {
  				shaHex = Integer.toHexString(digest[i] & 0xFF);
  				if (shaHex.length() < 2) {
  					hexstr.append(0);
  				}
  				hexstr.append(shaHex);
  			}
  			tmpStr= hexstr.toString();
  		} catch (Exception e) {
  			 System.out.print("\nAesException "+signature);
  			e.printStackTrace();
  			throw new AesException(AesException.ComputeSignatureError);
  		}
    	  
        System.out.print("\n 3.将sha1加密后的字符串可与tmpStr "+tmpStr);
        if(signature!=null){
        	
        	System.out.print("\n 3.将sha1加密后的字符串可与signature对比signature.toUpperCase() "+signature.toUpperCase());
        }
        System.out.print("\tmpStr  signature "+tmpStr+"|"+signature);
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.toUpperCase().equals(signature.toUpperCase()) : false;
    }
    public static boolean checkSignature(String signature, String timestamp,String nonce) {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
		 
//    	if(signature==null){
//    		signature = "nihao";
//				
//		 }
//    	if(timestamp==null){
//			 timestamp = Long.toString(System.currentTimeMillis() / 1000);
//			
//		 }
//		 if(nonce==null){
//			   nonce = UUID.randomUUID().toString();
//		 }
    	  System.out.print("\n checkSignature signature timestamp  nonce "+signature+"|"+timestamp+"|"+nonce);
      
    	  String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);
        
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
        	System.out.print("\n content.toString() "+content.toString());
            
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            
//加密
			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
            
           // tmpStr = byteToStr(digest);
            tmpStr=hexstr.toString();
            
            
            System.out.print("\n tmpStr "+tmpStr);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        
        
        
        content = null;
        System.out.print("\n 3.将sha1加密后的字符串可与signature对比signature "+signature);
        if(signature!=null){
        	
        	System.out.print("\n 3.将sha1加密后的字符串可与signature对比signature.toUpperCase() "+signature.toUpperCase());
        }
        System.out.print("\tmpStr  signature "+tmpStr+"|"+signature);
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.toUpperCase().equals(signature.toUpperCase()) : false;
    }

    /**
    * 方法名：byteToStr</br>
    * 详述：将字节数组转换为十六进制字符串</br>
    * 开发人员：souvc </br>
    * 创建时间：2015-9-29  </br>
    * @param byteArray
    * @return
    * @throws
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
    * 方法名：byteToHexStr</br>
    * 详述：将字节转换为十六进制字符串</br>
    * 开发人员：souvc</br>
    * 创建时间：2015-9-29  </br>
    * @param mByte
    * @return
    * @throws
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}