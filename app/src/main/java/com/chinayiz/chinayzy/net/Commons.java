package com.chinayiz.chinayzy.net;

/**
 * Created by Administrator on 2017/1/3.
 *  公有的API
 */

public class Commons {
    public static final String PAY="http://106.14.20.226/yzyProduct/v1/api";

    public static final String HOST="http://chinayiz.cn";
    /**
     * 主机地址
     */
    public static final String API = "http://106.14.20.226/yzyProduct/v1/api";
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
    public static final String NY_EATTHEME="/farm/eattheme";
    /**
     * 生态农业首页爱吃商品
     */
    public static final String NY_EATITEM = "/farm/eat";
    /**
     *根据商品id进入商铺主页
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
     *自营茶叶首页banner图
     */
    public static final String TEA_BANNER = "/farm/showTeaAdv";
    /**
     *获取生态农业二级分类code
     */
    public static final String TYPE_CODES = "/farm/showInitFarmType";
    /**
     *获取生态农业（二级子类）三级分类code
     */
    public static final String CLASS_CODES = "/farm/showTeaCode";
    /**
     * 获取商品相关简要信息
     */
    public static final String GOODS_DETAIL = "/farm/showGoodsDetail";
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
    public static final String ADDSHOPPINGCAR="/farm/addShoppingCar";
    /**
     * 购物车
     */
    public static final String SHOPCART="/farm/showShoppingCar";
    /**
     * 删除购物车商品
     */
    public static final String DELSHOPPINGCAR="/farm/delShoppingCar";

    /**
     * 编辑购物车完成
     */
    public static final String UPDATESHOPPINGCAR="/farm/updateShoppingCar";


    /**
     * 获取所有的搜索标签
     */
    public static final String GETSEARCHKEY="/farm/getSearchkey";
    /**
     * 生态农业首页搜索
     */
    public static final String SEARCHFARM="/farm/searchFarm";

    /**
     * 获取商品套餐
     */
    public static final String SHOWGOODSSTANDARD="/farm/showGoodsStandard";
    /**
     * 获取个人中心个人信息
     */
    public static final String GETPERSONALCENTER="/centre/getPersonalCenter";
    /**
     * 获取个人信息
     */
    public static final String GETUSERINFO="/centre/getUserinfo";

    /**
     * 编辑用户信息
     */
    public static final String EDITUSER="/centre/editUser";

    /**
     * 意见反馈
     */
    public static final String ADDIDEA="/centre/addIdea";

    /**
     * 获取个性标签
     */
    public static final String GETTAGS="/centre/getTags";

    /**
     * 添加自定义个性标签
     */
    public static final String ADDTAGS="/centre/addTags";
    /**
     * 修改完成个性标签
     */
    public static final String FINISHTAGS="/centre/finishTags";

    /**
     * 结算购物车选中的订单，预览订单
     */
    public static final String PREVIEWORDER="/centre/previewOrder";
    /**
     * 支付宝支付
     */
    public static final String ALIPAYORDER="/pay/aliPayOrder";
    /**
     * 微信支付支付
     */
    public static final String WXPAYORDER="/pay/wxPayOrder";

    /**
     * 博文点赞
     */
    public static final String DIZAN="/centre/dizan";
    /**
     * 博文收藏
     */
    public static final String CANCELCOLLECT="/centre/cancelCollect";

    /**
     * 获取收货地址
     */
    public static final String SHOWADDRESS="/centre/showAddress";
    /**
     * 编辑收货地址
     */
    public static final String EDITADDRESS="/centre/editAddress";

    /**
     * 将收货地址设置为默认
     */
    public static final String DEFAULTADDRESS="/centre/defaultAddress";
    /**
     *删除收货地址
     */
    public static final String DELETEADDRESS="/centre/deleteAddress";
    /**
     *新增收货地址
     */
    public static final String ADDADDRESS="/centre/addAddress";

    /**
     *宝贝收藏
     */
    public static final String SHOWGOODSCOLLECT="/centre/showGoodsCollect";
    /**
     *店铺收藏
     */
    public static final String SHOWSHOPCOLLECT="/centre/showShopCollect";
    /**
     *博文收藏
     */
    public static final String SHOWBLOGCOLLECT="/centre/showBlogCollect";
    /**
     *足迹列表
     */
    public static final String SHOWFOOTMARKS="/centre/showFootmarks";
    /**
     *删除足迹
     */
    public static final String DELETEFOOTMARK="/centre/deleteFootmark";
    /**
     * 关于我们
     */
    public static final String ABOUTUS="/h5/aboutus";

    /**
     * 发现详情
     */
    public static final String FXXQ="/h5/fxxq";
    /**
     * 我的积分
     */
    public static final String IM_GOLD="/centre/showPointEarnings";
    /**
     *积分交易列表
     */
    public static final String DEAL_LIST="/centre/showPointDealDetail";
    /**
     * 申请积分提现
     */
    public static final String GET_GOLD="/centre/withdraw";
    /**
     * 获取积分
     */
    public static final String GET_GOLD_MODE="/h5/zqjf";
    /**
     * 提现规则
     */
    public static final String GET_GOLD_RULE="/h5/txgz";
    /**
     * 积分规则
     */
    public static final String GOLD_RULE="/h5/jfgz";
    /**
     * 分享二维码
     */
    public static final String FXCODE="/h5/fxcode";
/**
 * 物流信息
 */
public static final String LOGISTICS_INFO="/h5/wlxx";
    /**
     * 我的订单
     */
    public static final String ORDER_STATE="/centre/showOrders";
    /**
     * 订单详情
     */
    public static final String ORDER_DETAIL="/centre/showOrderDetail";
    /**
     * 删除订单
     */
    public static final String DELETE_ORDER="/centre/deleteOrder";
    /**
     * 取消订单
     */
    public static final String CANCEL_ORDER="/centre/cancelOrder";
    /**
     * 确认收货
     */
    public static final String CONFIRM_ORDER ="/centre/confirmGoods";
    /**
     * 评价订单
     */
    public static final String  COMMENT_ORDER="/centre/commentOrder";
    /**
     * 快速支付
     */
    public static final String FAST_PAY="/pay/fastPay";
    /**
     *售后列表
     */
    public static final String AFTER_LIST="/salesreturn/salesReturnList";
    /**
     *售后申请
     */
    public static final String AFTER_RESQUEST="/salesreturn/toApplySalesafter";

}
