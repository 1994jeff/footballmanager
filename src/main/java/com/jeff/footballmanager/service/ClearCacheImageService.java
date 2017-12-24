package com.jeff.footballmanager.service;

import com.jeff.footballmanager.utils.ImageCacheUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ClearCacheImageService extends Service {

	private static int bit = 1024;
	private static int b = 1024;
	private static int mb30 = 10;
	private static int mb50 = 20;
	private static int mb60 = 30;
	
	private static double sixtyPrecent = 0.8;
	private static double fiftyPrecent = 0.65;
	private static double thirtyPrecent = 0.3;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		ImageCacheUtils cacheUtils = new ImageCacheUtils(this);
		Integer size = ImageCacheUtils.getFolderSize();
		Log.i("jeff", "图片已缓存大小："+size+"B"+"-----");

		if(size>bit*b*(mb60+1)){
			ImageCacheUtils.clearLocalBitmap(1);
		}

		if(size>bit*b*mb60){
			ImageCacheUtils.clearLocalBitmap(sixtyPrecent);
		}
		if(size>bit*b*mb50){
			ImageCacheUtils.clearLocalBitmap(fiftyPrecent);
		}
		if(size>bit*b*mb30){
			ImageCacheUtils.clearLocalBitmap(thirtyPrecent);
		}
		
		Integer size2 = ImageCacheUtils.getFolderSize();
		Log.i("jeff", "清除旧照片后已缓存大小："+size2+"B"+"-----");
		
		stopSelf();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}


}
