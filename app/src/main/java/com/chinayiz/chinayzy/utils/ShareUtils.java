package com.chinayiz.chinayzy.utils;

import android.content.Context;

import com.orhanobut.logger.Logger;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * 分享工具类
 * Created by Administrator on 2015/1/20.
 */

public class ShareUtils {
    public static final int WECHATMOMENTS=0;
    public static final int WECHAT=1;
    public static final int SINOWEIBO=2;
    public static final int QQPERSON=3;
    private static String shareImageUrl="http://chinayiz.cn/v1/2017/0330/f8bafa64-bbea-486f-9efd-0495ef266a47.png";
    /**
     * 分享
     *
     * @param dis     内容
     * @param title    标题
     * @param url     路径
     */
    public static void turnToShare(int type, String dis, String title , String imageUrl, String url, PlatformActionListener paListener, Context context) {
        switch (type){
            case WECHATMOMENTS:
                //朋友圈的分享
                WechatMoments.ShareParams momentsp=new WechatMoments.ShareParams();
                momentsp.setTitle(title);
                momentsp.setImageUrl(imageUrl);
                momentsp.setText(dis);
                momentsp.setUrl(url);
                momentsp.setShareType(Platform.SHARE_WEBPAGE);
                Platform moments = ShareSDK.getPlatform (context,WechatMoments.NAME);
                moments.SSOSetting(true);
// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                moments.setPlatformActionListener (paListener);
// 执行图文分享
                moments.share(momentsp);
                break;
            case WECHAT:
                //微信分享
                Wechat.ShareParams wechatsp=new  Wechat.ShareParams();
                wechatsp.setTitle(title);
                wechatsp.setImageUrl(imageUrl);
                wechatsp.setText(dis);
                wechatsp.setUrl(url);

                wechatsp.setShareType(Platform.SHARE_WEBPAGE);
                Platform wechat = ShareSDK.getPlatform (context,Wechat.NAME);
// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                wechat.setPlatformActionListener (paListener);
// 执行图文分享
                wechat.share(wechatsp);
                break;
            case SINOWEIBO:
                //新浪微博分享
                SinaWeibo.ShareParams weibosp = new SinaWeibo.ShareParams();
                weibosp.setText(title);
                weibosp.setImageUrl(imageUrl);
                weibosp.setText(dis);
                weibosp.setUrl(url);

                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.setPlatformActionListener(paListener); // 设置分享事件回调
                // 执行图文分享
                weibo.share(weibosp);
                break;
            case QQPERSON:
                //QQ分享
                QQ.ShareParams qqsp=new QQ.ShareParams();
                qqsp.setTitle(title);
                qqsp.setImageUrl(imageUrl);
                qqsp.setText(dis);
                qqsp.setTitleUrl(url);
                Platform qq = ShareSDK.getPlatform (QQ.NAME);
// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                qq.setPlatformActionListener (paListener);
// 执行图文分享
                qq.share(qqsp);
                Logger.i("imageUrl"+imageUrl+"dis"+dis+"url"+url);
                break;

        }
    }


}
