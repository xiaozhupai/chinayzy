package com.chinayiz.chinayzy.net;

import com.chinayiz.chinayzy.APP;

/**
 * Created by Administrator on 2017/1/3.
 *  公有的API
 */

public class Commons {
    public static final boolean isDebug =APP.APP_DBG;
    /**
     * 主机地址
     */
    public static final String API = isDebug ? "http://192.168.200.8:8081/yzyProduct/v1/api" : "http://106.14.20.226/yzyProduct/v1/api";
    public static final String PAY = isDebug ? "http://192.168.200.132:8081/yzyProduct/v1/api" : "http://106.14.20.226/yzyProduct/v1/api";
    public static final String SHARE = isDebug ? "http://192.168.200.8:8081/yzyProduct/v1/api" : "http://www.chinayiz.cn/yzyProduct/v1/api";
    public static final String HOST = "http://yzy-app-img.oss-cn-shanghai.aliyuncs.com";
    //获得accss_token
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //刷新 refresh_token
    public static final String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    //微信获得用户信息
    public static final String WECHAT_USERINFO = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 添加购物车
     */

    public static final String ADD_CAR = "ClickAddCar";
    /**
     * 检查更新地址
     */

    public static final String UPDATA = "/centre/updateVersion";
    /**
     * 平台首页广告
     */
    public static final String MAIN_BANNER = "/farm/showIndexAd";
    /**
     * 生态农业首页banner图
     */
    public static final String NY_BANNER = "/farm/showAdvPic";

    /**
     * 生态农业首页精选推荐
     */
    public static final String NY_RECOMMENT = "/farm/recomment";
    /**
     * 生态农业首页特色商品
     */
    public static final String NY_FEATURE = "/farm/feature";
    /**
     * 生态农业首页爱吃主题
     */
    public static final String NY_EATTHEME = "/farm/eattheme";
    /**
     * 生态农业首页爱吃商品
     */
    public static final String NY_EATITEM = "/farm/eat";
    /**
     * 根据商品id进入商铺主页
     */
    public static final String STORE_HOME = "/farm/showShop";
    /**
     * 添加关注店铺
     */
    public static final String ATTENTION_STORE = "/farm/addShopAttention";
    /**
     * 取消关注店铺
     */
    public static final String UNATTENTION_STORE = "/farm/delShopAttention";
    /**
     * 根据店铺商品分类和排序查询商品
     */
    public static final String FORTYPEBY_GOODSS = "/farm/inShopGoods";
    /**
     * 自营茶叶首页banner图
     */
    public static final String TEA_BANNER = "/farm/showTeaAdv";
    /**
     * 获取生态农业二级分类code
     */
    public static final String TYPE_CODES = "/farm/showInitFarmType";
    /**
     * 获取生态农业（二级子类）三级分类code
     */
    public static final String CLASS_CODES = "/farm/showTeaCode";
    /**
     * 获取商品相关简要信息
     */
    public static final String GOODS_DETAIL = "/farm/showGoodsDetail";
    /**
     * 农业分类二级菜单商品列表
     */
    public static final String GOODS_SET = "/farm/showTeaCodeDetail";
    /**
     * 获取商品图文详情
     */
    public static final String GOODS_PICDETAIL = "/h5/spxqjs";
    /**
     * 获取商品规格详情
     */
    public static final String GOODS_CPGG = "/h5/cpgg";
    /**
     * 获取商品套餐(组合)信息
     */
    public static final String GOODS_GROUP = "/farm/showGoodsStandard";
    /**
     * 获取商品详情相关商品
     */
    public static final String GOODS_RELATED = "/farm/relatedProducts";
    /**
     * 收藏商品
     */
    public static final String GOODS_COLLECT = "/farm/collect";
    /**
     * 取消收藏商品
     */
    public static final String GOODS_UNCOLLECT = "/farm/cancelGoodsCollect";
    /**
     * 获取评论列表
     */
    public static final String COMMENT_LIST = "/farm/showComments";
    /**
     * 注册
     */
    public static final String REGISTER = "/user/register";
    /**
     * 登录
     */
    public static final String LOGIN = "/user/login";
    /**
     * 融云token
     */
    public static final String TOKEN = "/user/token";
    /**
     * 找回密码
     */
    public static final String BACKPWD = "/user/backpwd";
    /**
     * 发送验证码
     */
    public static final String SRYCODE = "/user/srycode";
    /**
     * 第三方登录
     */
    public static final String THIRD = "/user/third";
    /**
     * 会员权益和用户协议
     */
    public static final String GETHYQYANDYHXY = "/user/gethyqyandyhxy";
    /**
     * 获取用户基础数据
     */
    public static final String BASEDATA = "/user/basedata";


    /**
     * 发现类型
     */
    public static final String FINDTYPE = "/farm/findType";
    /**
     * 是否点赞 是否收藏
     */
    public static final String ISCOLLECTORPRAISE = "/farm/isCollectOrPraise";
    /**
     * 发现列表
     */

    public static final String FINDBLOGBYTYPE = "/farm/findBlogByType";
    /**
     * 加入购物车
     */
    public static final String ADDSHOPPINGCAR = "/farm/addShoppingCar";
    /**
     * 购物车
     */
    public static final String SHOPCART = "/farm/showShoppingCar";
    /**
     * 删除购物车商品
     */
    public static final String DELSHOPPINGCAR = "/farm/delShoppingCar";

    /**
     * 编辑购物车完成
     */
    public static final String UPDATESHOPPINGCAR = "/farm/updateShoppingCar";


    /**
     * 获取所有的搜索标签
     */
    public static final String GETSEARCHKEY = "/farm/getSearchkey";
    /**
     * 搜索获取品牌
     */
    public static final String GETBRANDS = "/farm/getBrands";

    /**
     * 生态农业首页搜索
     */
    public static final String SEARCHFARM = "/farm/searchFarm";


    /**
     * 获取商品套餐
     */
    public static final String SHOWGOODSSTANDARD = "/farm/showGoodsStandard";
    /**
     * 获取个人中心个人信息
     */
    public static final String GETPERSONALCENTER = "/centre/getPersonalCenter";
    /**
     * 获取个人信息
     */
    public static final String GETUSERINFO = "/centre/getUserinfo";

    /**
     * 编辑用户信息
     */
    public static final String EDITUSER = "/centre/editUser";

    /**
     * 意见反馈
     */
    public static final String ADDIDEA = "/centre/addIdea";

    /**
     * 获取个性标签
     */
    public static final String GETTAGS = "/centre/getTags";

    /**
     * 添加自定义个性标签
     */
    public static final String ADDTAGS = "/centre/addTags";
    /**
     * 修改完成个性标签
     */
    public static final String FINISHTAGS = "/centre/finishTags";

    /**
     * 结算购物车选中的订单，预览订单
     */
    public static final String PREVIEWORDER = "/centre/previewOrder1";
    /**
     * 活动预览订单
     */
    public static final String PREVIEWCROWDFORDER = "/crowdf/previewCrowdfOrder";

    /**
     * 支付宝支付
     */
    public static final String ALIPAYORDER = "/pay/aliPayOrder";
    /**
     * 微信支付支付
     */
    public static final String WXPAYORDER = "/pay/wxPayOrder";
    /**
     * 亿众币支付
     */
    public static final String YZBPAYORDER = "/pay/yzbPayOrder";

    /**
     * 博文点赞
     */
    public static final String DIZAN = "/centre/dizan";
    /**
     * 博文收藏
     */
    public static final String CANCELCOLLECT = "/centre/cancelCollect";

    /**
     * 获取收货地址
     */
    public static final String SHOWADDRESS = "/centre/showAddress";
    /**
     * 编辑收货地址
     */
    public static final String EDITADDRESS = "/centre/editAddress";

    /**
     * 将收货地址设置为默认
     */
    public static final String DEFAULTADDRESS = "/centre/defaultAddress";
    /**
     * 删除收货地址
     */
    public static final String DELETEADDRESS = "/centre/deleteAddress";
    /**
     * 新增收货地址
     */
    public static final String ADDADDRESS = "/centre/addAddress";

    /**
     * 宝贝收藏
     */
    public static final String SHOWGOODSCOLLECT = "/centre/showGoodsCollect";
    /**
     * 店铺收藏
     */
    public static final String SHOWSHOPCOLLECT = "/centre/showShopCollect";
    /**
     * 博文收藏
     */
    public static final String SHOWBLOGCOLLECT = "/centre/showBlogCollect";
    /**
     * 足迹列表
     */
    public static final String SHOWFOOTMARKS = "/centre/showFootmarks";
    /**
     * 删除足迹
     */
    public static final String DELETEFOOTMARK = "/centre/deleteFootmark";
    /**
     * 关于我们
     */
    public static final String ABOUTUS = "/h5/aboutus";

    /**
     * 发现详情
     */
    public static final String FXXQ = "/h5/fxxq";
    /**
     * 我的积分
     */
    public static final String IM_GOLD = "/centre/showPointEarnings";
    /**
     * 积分交易列表
     */
    public static final String DEAL_LIST = "/centre/showPointDealDetail";
    /**
     * 申请积分提现
     */
    public static final String GET_GOLD = "/centre/withdraw";
    /**
     * 获取积分
     */
    public static final String GET_GOLD_MODE = "/h5/zqjf";
    /**
     * 提现规则
     */
    public static final String GET_GOLD_RULE = "/h5/txgz";
    /**
     * 积分规则
     */
    public static final String GOLD_RULE = "/h5/jfgz";
    /**
     * 分享二维码
     */
    public static final String FXCODE = "/h5/fxcode";
    /**
     * 物流信息
     */
    public static final String LOGISTICS_INFO = "/h5/wlxx";
    /**
     * 完善资料
     */
    public static final String EDIE_USERINFO = "/h5/demo";

    /**
     * 资料是否完善
     */
    public static final String USERINFO_ISOK = "/user/isresearch";
    /**
     * 我的订单
     */
    public static final String ORDER_STATE = "/centre/showOrders";
    /**
     * 订单详情
     */
    public static final String ORDER_DETAIL = "/centre/showOrderDetail";
    /**
     * 删除订单
     */
    public static final String DELETE_ORDER = "/centre/deleteOrder";
    /**
     * 取消订单
     */
    public static final String CANCEL_ORDER = "/centre/cancelOrder";
    /**
     * 确认收货
     */
    public static final String CONFIRM_ORDER = "/centre/confirmGoods";
    /**
     * 评价订单
     */
    public static final String COMMENT_ORDER = "/centre/commentOrder";
    /**
     * 快速支付
     */
    public static final String FAST_PAY = "/pay/fastPay";
    /**
     * 售后列表
     */
    public static final String AFTER_LIST = "/salesreturn/salesReturnList";
    /**
     * 售后申请
     */
    public static final String AFTER_RESQUEST = "/salesreturn/toApplySalesafter";
    /**
     * 推荐信息
     */
    public static final String RECOMMEND_INFO = "/centre/myRecommendCode";

    /**
     * 亿众商城
     */
    public static final String SEARCHMALLGOODS = "/mall/searchMallGoods";

    /**
     * 商城首页模块菜单按钮图
     */
    public static final String HOME_MODEL = "/shouye/shouyemodule1";
    /**
     * 商城首页主题一
     */
    public static final String HOME_THEME1 = "/shouye/showIndexAdTwo";
    /**
     * 商城首页主题二
     */
    public static final String HOME_THEME2 = "/shouye/showIndexAdThree";
    /**
     * 商城首页商品集合一(原生态)
     */
    public static final String HOME_LIST1 = "/shouye/showGoodsOne";
    /**
     * 商城首页商品集合二（吃货必备）
     */
    public static final String HOME_LIST2 = "/shouye/showGoodsTwo";
    /**
     * 商城首页主题商品（1 爱时尚，2 世界硒都，3 奢侈品）
     */
    public static final String HOME_THEME_GOODS="/shouye/showGoodsThree";
    /**
     * 首页主要活动主题
     */
    public static final String HONE_ACTIVITYS="/shouye/shouyeactivity";
    /**
     * 首页倒计时抢购(众筹)商品橱窗
     */
    public static final String HOME_ZHONGCHOU="/shouye/shouyecrowdfund";
    /**
     * 首页亿众头条新闻
     */
    public static final String HOME_NEWS="/shouye/newsflash";
    /**
     * 商城首页主题商品（为你推荐）
     */
    public static final String HOME_REXIAO = "/shouye/showBestGoods";
    /**
     * 现金券
     */
    public static final String XJJUAN = "/h5/xjjuan";
    /**
     * 推荐用户列表
     */
    public static final String FINDRECOMMENDCOUNT = "/h5/findRecommendCount";
    /**
     * 完善个人资料
     */
    public static final String AUTHIDCARD = "/centre/authIdCard";


    /**
     * 亿众商城商品搜索接口
     */
    public static final String STORE_HOME_SEARCH = "/mall/searchMallGoods";
    /**
     * 商品板块名称
     */
    public static final String STORE_HOME_TABS = "/mall/showMallType";
    /**
     * 亿众商城首页广告图
     */
    public static final String STORE_HOME_BANNER = "/mall/showMallAd";
    /**
     * 商城商品主题集合
     */
    public static final String STORE_HOME_THEME = "/mall/malltheme";
    /**
     * 根据板块itemcode查询商品
     */
    public static final String STORE_HOME_GOODSS = "/mall/showMallGoods";

    /**
     * 首页中奖弹出框
     */
    public static final String OPENWINNER = "/crowdf/openWinner";

    /**
     * 获奖详情
     */
    public static final String WINNERDETAIL = "/crowdf/winnerdetail";

    /**
     *  兑换亿众币
     */
    public static final String CHANGEYZB = "/crowdf/changeYzb";
    /**
     *  确认收货地址
     */
    public static final String CONFIRMADDRESS = "/crowdf/confirmAddress";
    /**
     *  获得默认的收货地址
     */
    public static final String GETDEFAULTADDRESS = "/crowdf/getdefaultAddress";
    /**
     *  获奖记录
     */
    public static final String CROWDFRECORD = "/crowdf/crowdfRecord";
    /**
     *  获取分享众筹活动
     */
    public static final String SHARECROWDFMESSAGE = "/crowdf/shareCrowdfMessage";
    /**
     *
     * 活动物流信息
     */
    public static final String CROWDFWLXX = "/h5/crowdfwlxx";
    /**
     *
     *  活动确认收货
     */
    public static final String CONFIRMSHOUHUO = "/crowdf/confirmShouhuo";

    /**
     *
     *  可用优惠券列表
     */
    public static final String CANUSECOUPON ="/centre/canUseCoupon";

    /**
     *
     *  获取购物车数量
     */
    public static final String SHOPPINGCARTCOUNT ="/user/shoppingCarCount";
    /**
     *  分享商品信息
     */
    public static final String SHARE_GOODS_INFO ="/centre/shareGoods";
}
