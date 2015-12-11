package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/12/11.
 */
public class ConcernCommodityObject implements Serializable{

    private static final long serialVersionUID = 8978832584513611995L;
    /**
     * Status : true
     * Data : [{"logoUrl":"shop/image/product/20150406/2015040609510001522610.png","productId":998,"productName":"微星 笔记本","salesPrice":5999},{"logoUrl":"shop/image/product/20150406/2015040608360000111623.jpg","productId":979,"productName":"三星 手机","salesPrice":2000},{"logoUrl":"shop/image/product/20150402/2015040217290005060097.jpg","productId":863,"productName":"美肤宝 洗面奶","salesPrice":55},{"logoUrl":"shop/image/product/20150401/2015040114350001236432.png","productId":744,"productName":" 菲拉格慕 香水","salesPrice":300},{"logoUrl":"shop/image/product/20150416/2015041617500000796233.png","productId":1559,"productName":"耐克 运动T恤","salesPrice":150}]
     */

    private boolean Status;
    /**
     * logoUrl : shop/image/product/20150406/2015040609510001522610.png
     * productId : 998
     * productName : 微星 笔记本
     * salesPrice : 5999
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

        private static final long serialVersionUID = 3036578853163912430L;
        private String logoUrl;
        private int productId;
        private String productName;
        private float salesPrice;

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setSalesPrice(float salesPrice) {
            this.salesPrice = salesPrice;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public float getSalesPrice() {
            return salesPrice;
        }
    }
}
