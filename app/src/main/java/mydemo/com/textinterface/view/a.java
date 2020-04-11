package mydemo.com.textinterface.view;

/**
 * 作者: Wang on 2019/6/16 20:17
 * 寄语：加油！相信自己可以！！！
 */


public class a {

    /**
     * returnCode : 10
     * returnMsg : 查询成功
     * dataCount : 0
     * returnData : {"storageSkuList":[{"id":25034,"vendorId":2010,"organCode":"101","organName":"总部机构","storeCode":"1011","storeName":"总部仓库","storageCode":"custom_A01","storageName":"自定义货道01","firstStorageCode":"custom_A","firstStorageName":"自定义库区A","secondStorageCode":"custom_A01","secondStorageName":"自定义货道01","thirdStorageCode":"custom_A0101","thirdStorageName":"自定义货位01","skuCode":"expiry007","skuName":"效期商品007","barcode":"1553844196598","stockUnit":"个","bigUnit":"个","unitNum":1.0,"specMemo":"","storageAttr":0,"validDays":365,"brandCode":"10","brandName":"默认品牌","icatCode":"10","icatName":"生鲜","firstBrandCode":"10","firstIcatCode":"10","firstIcatName":"生鲜","df":0,"createDate":"2019-06-13 15:41:41","modifyDate":"2019-06-16 08:30:21","creator":"testyin","creatorRealName":"testyin","mender":"testyin","menderRealName":"testyin","version":6,"firstIcatView":"[10]生鲜","secondIcatView":"","firstStorageView":"[custom_A]自定义库区A","secondStorageView":"[custom_A01]自定义货道01","thirdStorageView":"[custom_A0101]自定义货位01","storageAttrView":"常温"}],"hierachies":{"id":2846,"vendorId":2010,"storageCode":"custom_A","storageName":"自定义库区A","storageType":1,"parentCode":"","level":1,"leafNode":0,"isInit":0,"organCode":"101","organName":"总部机构","storeCode":"1011","storeName":"总部仓库","asused":0,"usable":1,"memo":"自定义库区A","df":0,"createDate":"2019-06-12 15:52:30","modifyDate":"2019-06-12 15:52:30","parentName":"","subList":[{"id":2847,"vendorId":2010,"storageCode":"custom_A01","storageName":"自定义货道01","storageType":1,"parentCode":"custom_A","level":2,"leafNode":0,"isInit":0,"organCode":"101","organName":"总部机构","storeCode":"1011","storeName":"总部仓库","asused":0,"usable":1,"memo":"自定义货道01","df":0,"createDate":"2019-06-12 15:53:37","modifyDate":"2019-06-12 15:53:37","parentName":"自定义库区A","subList":[{"id":2848,"vendorId":2010,"storageCode":"custom_A0101","storageName":"自定义货位01","storageType":1,"parentCode":"custom_A01","level":3,"leafNode":1,"isInit":0,"organCode":"101","organName":"总部机构","storeCode":"1011","storeName":"总部仓库","asused":0,"usable":1,"memo":"自定义货位01","df":0,"createDate":"2019-06-12 15:54:25","modifyDate":"2019-06-12 15:54:25","parentName":"自定义货道01"}]}]}}
     * returnObject : null
     */

    private int returnCode;
    private String returnMsg;
    private int dataCount;
    private String returnData;
    private Object returnObject;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }


}
