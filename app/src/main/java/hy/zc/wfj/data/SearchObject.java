package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/12/7.
 */
public class SearchObject implements Serializable{


    private static final long serialVersionUID = 2392920906559098806L;
    /**
     * Status : true
     * Data : [{"logoImg":"shop/image/product/20150406/2015040608360000111623.jpg","productFullName":"三星 手机 [金色,5]","productId":979,"productName":"三星 手机","productTypeId":755,"salesPrice":2000,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150406/2015040608410001614456.jpg","productFullName":"HTC Desire 826t 典雅白 手机 [白色,5.5]","productId":980,"productName":"HTC Desire 826t 典雅白 手机","productTypeId":755,"salesPrice":3000,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150406/2015040608460000078478.png","productFullName":"酷派 大神 F2（8675-HD） 手机 [白色,5.5]","productId":981,"productName":"酷派 大神 F2（8675-HD） 手机","productTypeId":755,"salesPrice":1090,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150406/2015040608500005918733.png","productFullName":"魅族 手机 [灰色,5.5]","productId":982,"productName":"魅族 手机","productTypeId":755,"salesPrice":1399,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150503/2015050309060002929884.png","productFullName":"摩托罗拉 moto g （XT1079）8GB 手机 [白色,5]","productId":983,"productName":"摩托罗拉 moto g （XT1079）8GB 手机","productTypeId":755,"salesPrice":1299,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150503/2015050308490003584716.png","productFullName":"摩托罗拉 moto g （XT1079）8GB 手机 [白色,5]","productId":987,"productName":"摩托罗拉 moto g （XT1079）8GB 手机","productTypeId":755,"salesPrice":1299,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150406/2015040609230004523934.png","productFullName":"联想 黄金斗士Note8(A938t)增强版 手机 [红色,6]","productId":990,"productName":"联想 黄金斗士Note8(A938t)增强版 手机","productTypeId":755,"salesPrice":898,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150406/2015040609300004239249.png","productFullName":"oppo 手机 [白色,5.2]","productId":993,"productName":"oppo 手机","productTypeId":755,"salesPrice":3999,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150406/2015040609350005151980.png","productFullName":"（Apple） iPhone 6 Plus 手机 [金色,5.5]","productId":995,"productName":"（Apple） iPhone 6 Plus 手机","productTypeId":755,"salesPrice":5788,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150430/2015043017330003756796.png","productFullName":"华为 手机 [金色,5]","productId":1181,"productName":"华为 手机","productTypeId":755,"salesPrice":1599,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150409/2015040908530000011547.jpg","productFullName":"苹果 手机 [白色,5]","productId":1182,"productName":"苹果 手机","productTypeId":755,"salesPrice":5088,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150409/2015040909090000839242.jpg","productFullName":"小米 手机 [白色,6]","productId":1184,"productName":"小米 手机","productTypeId":755,"salesPrice":899,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150409/2015040909490000182761.jpg","productFullName":"VIVO 手机 [白色,5]","productId":1189,"productName":"VIVO 手机","productTypeId":755,"salesPrice":2389,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150410/2015041011210002782419.jpg","productFullName":"艾肯（iCON） 明盾iPhone6plus全透明手机壳苹果6plus保护套iPhone5.5外壳 迷幻金 [白]","productId":1341,"productName":"艾肯（iCON） 明盾iPhone6plus全透明手机壳苹果6plus保护套iPhone5.5外壳 迷幻金","productTypeId":1385,"salesPrice":88,"shopName":"OPPO专卖店","totalSales":0},{"logoImg":"shop/image/product/20150424/2015042409530002242639.png","productFullName":"苹果 手机","productId":1854,"productName":"苹果 手机","productTypeId":755,"salesPrice":1200,"shopName":"华威科技","totalSales":0},{"logoImg":"shop/image/product/20150424/2015042413490001077728.png","productFullName":"苹果 手机","productId":1855,"productName":"苹果 手机","productTypeId":755,"salesPrice":1200,"shopName":"华威科技","totalSales":0}]
     */

    private boolean Status;
    /**
     * logoImg : shop/image/product/20150406/2015040608360000111623.jpg
     * productFullName : 三星 手机 [金色,5]
     * productId : 979
     * productName : 三星 手机
     * productTypeId : 755
     * salesPrice : 2000
     * shopName : OPPO专卖店
     * totalSales : 0
     */

    private List<DataEntity> Data;

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public void setData(List<DataEntity> Data) {
        this.Data = Data;
    }

    public boolean isStatus() {
        return Status;
    }

    public List<DataEntity> getData() {
        return Data;
    }

    public static class DataEntity implements Serializable{

        private static final long serialVersionUID = 7493718450817806135L;
        private String logoImg;
        private String productFullName;
        private int productId;
        private String productName;
        private int productTypeId;
        private float salesPrice;
        private String shopName;
        private int totalSales;

        public void setLogoImg(String logoImg) {
            this.logoImg = logoImg;
        }

        public void setProductFullName(String productFullName) {
            this.productFullName = productFullName;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setProductTypeId(int productTypeId) {
            this.productTypeId = productTypeId;
        }

        public void setSalesPrice(float salesPrice) {
            this.salesPrice = salesPrice;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public void setTotalSales(int totalSales) {
            this.totalSales = totalSales;
        }

        public String getLogoImg() {
            return logoImg;
        }

        public String getProductFullName() {
            return productFullName;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getProductTypeId() {
            return productTypeId;
        }

        public float getSalesPrice() {
            return salesPrice;
        }

        public String getShopName() {
            return shopName;
        }

        public int getTotalSales() {
            return totalSales;
        }
    }
}
