package com.supply.common;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 唯一标识符（订单号）生成器
 * 
 * @author ZGang
 * **/
public class Identify {

	public static Integer ident=0;
	public static synchronized String getIdent(String prefix){
		if(ident >= 99999){
			ident = 0;
		}
	
		String identStr = String.valueOf(ident);
		int it = identStr.length();
		
		for(int i=0;i<=3-it;i++){
			identStr = "0"+identStr;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		identStr = dateStr + identStr;
		
		if(prefix != null){
			identStr = prefix + identStr;
		}
		ident = ident+1;
		return identStr;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			System.out.println(getIdent("T"));
		}
	
	}
}
