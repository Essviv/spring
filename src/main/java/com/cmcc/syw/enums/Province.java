package com.cmcc.syw.enums;

/**
 * Created by sunyiwei on 2016/1/15.
 */
public enum Province {
    BEI_JING("北京","beijing"),
    SHANG_HAI("上海","shanghai"),
    TIAN_JIN("天津","tianjin"),
    CHONG_QING("重庆","chongqing"),
    XIANG_GANG("香港","xianggang"),
    AO_MEN("澳门","aomen"),
    AN_HUI("安徽","anhui"),
    FU_JIAN("福建","fujian"),
    GUANG_DONG("广东","guangdong"),
    GUANG_XI("广西","guangxi"),
    GUI_ZHOU("贵州","guizhou"),
    GAN_SU("甘肃","gansu"),
    HAI_NAN("海南","hainan"),
    HE_BEI("河北","hebei"),
    HE_NAN("河南","henan"),
    HEI_LONG_JIANG("黑龙江","heilongjiang"),
    HU_BEI("湖北","hubei"),
    HU_NAN("湖南","hunan"),
    JI_LIN("吉林","jilin"),
    JIANG_SU("江苏","jiangsu"),
    JIANG_XI("江西","jiangxi"),
    LIAO_NING("辽宁","liaoning"),
    NEI_MENG_GU("内蒙古","neimenggu"),
    NING_XIA("宁夏","ningxia"),
    QING_HAI("青海","qinghai"),
    SHAN_XI("陕西","shanxi"),
    SHANN_XI("山西","shannxi"),
    SHAN_DONG("山东","shandong"),
    SI_CHUAN("四川","sichuan"),
    TAI_WAN("台湾","taiwan"),
    XI_ZANG("西藏","xizang"),
    XIN_JIANG("新疆","xinjiang"),
    YUN_NAN("云南","yunnan"),
    ZHE_JIANG("浙江","zhejiang");

    private String name;
    private String suffix;

    Province(String name, String suffix) {
        this.name = name;
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public static Province getByName(String name){
        for (Province province : Province.values()) {
            if(province.getName().equals(name)){
                return province;
            }
        }

        return null;
    }
}
