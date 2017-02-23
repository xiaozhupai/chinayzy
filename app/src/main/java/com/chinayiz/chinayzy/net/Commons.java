package com.chinayiz.chinayzy.net;

/**
 * Created by Administrator on 2017/1/3.
 *  公有的API
 */

public class Commons {
    /**
     * 主机地址
     */
    public static final String API = "http://192.168.1.8:8081/yzyProduct/v1/api";
    /**
     * 检查更新地址
     */
    public static final String UPDATE = "http://rap.taobao.org/mockjs/12395/";
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
     *自营茶叶首页子页分类字段
     */
    public static final String TEA_TYPELIST = "/farm/showTeaCode";
    /**
     *自营茶叶首页子页分类字段查询农产品
     */
    public static final String TEA_TEALIST = "/farm/showTeaCodeDetail";
    /**
     * 获取商品相关简要信息
     */
    public static final String GOODS_DETAIL = "/farm/showGoodsDetail";
    /**
     * 获取商品图文详情
     */
    public static final String GOODS_PICDETAIL = "/farm/showPicDetail";
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
     * 发表评论
     */
    public static final String COMMENTORDER="/centre/commentOrder";
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

}
