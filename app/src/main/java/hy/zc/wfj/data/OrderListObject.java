package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/12/14.
 */
public class OrderListObject implements Serializable{

    private static final long serialVersionUID = -710891667491152880L;
    /**
     * Status : true
     * Data : [{"finalAmount":"150.00","list":[{"count":0,"createTime":{"date":11,"day":5,"hours":16,"minutes":26,"month":11,"nanos":0,"seconds":11,"time":1449822371000,"timezoneOffset":-480,"year":115},"customerId":459,"freightPrice":0,"logoImage":"shop/image/product/20150416/2015041617500000796233.png","orderDetailId":1492,"ordersId":1180,"ordersNo":"2015121116261111","productFullName":"耐克 运动T恤 [黑色]","productId":1559,"salesPrice":150,"shopInfoId":275,"shopName":"金利来专卖店"}],"ordersId":"1180","ordersNo":"2015121116261111"},{"finalAmount":"6826.00","list":[{"count":0,"createTime":{"date":11,"day":5,"hours":16,"minutes":25,"month":11,"nanos":0,"seconds":40,"time":1449822340000,"timezoneOffset":-480,"year":115},"customerId":459,"freightPrice":0,"logoImage":"shop/image/product/20150402/2015040216370000821078.jpg","orderDetailId":1489,"ordersId":1179,"ordersNo":"2015121116254055","productFullName":"麦包包 手提包 [其它]","productId":845,"salesPrice":298,"shopInfoId":272,"shopName":"OPPO专卖店"},{"count":0,"createTime":{"date":11,"day":5,"hours":16,"minutes":25,"month":11,"nanos":0,"seconds":40,"time":1449822340000,"timezoneOffset":-480,"year":115},"customerId":459,"freightPrice":0,"logoImage":"shop/image/product/20150406/2015040609510001522610.png","orderDetailId":1490,"ordersId":1179,"ordersNo":"2015121116254055","productFullName":"微星 笔记本 [黑]","productId":998,"salesPrice":5999,"shopInfoId":272,"shopName":"OPPO专卖店"},{"count":0,"createTime":{"date":11,"day":5,"hours":16,"minutes":25,"month":11,"nanos":0,"seconds":40,"time":1449822340000,"timezoneOffset":-480,"year":115},"customerId":459,"freightPrice":0,"logoImage":"shop/image/product/20150403/2015040308410001199700.jpg","orderDetailId":1491,"ordersId":1179,"ordersNo":"2015121116254055","productFullName":"Story Of Shanghai/上海故事 丝巾","productId":877,"salesPrice":529,"shopInfoId":272,"shopName":"OPPO专卖店"}],"ordersId":"1179","ordersNo":"2015121116254055"}]
     */

    private boolean Status;
    /**
     * finalAmount : 150.00
     * list : [{"count":0,"createTime":{"date":11,"day":5,"hours":16,"minutes":26,"month":11,"nanos":0,"seconds":11,"time":1449822371000,"timezoneOffset":-480,"year":115},"customerId":459,"freightPrice":0,"logoImage":"shop/image/product/20150416/2015041617500000796233.png","orderDetailId":1492,"ordersId":1180,"ordersNo":"2015121116261111","productFullName":"耐克 运动T恤 [黑色]","productId":1559,"salesPrice":150,"shopInfoId":275,"shopName":"金利来专卖店"}]
     * ordersId : 1180
     * ordersNo : 2015121116261111
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

        private static final long serialVersionUID = 4418329602614659828L;
        private String finalAmount;
        private String ordersId;
        private String ordersNo;
        /**
         * count : 0
         * createTime : {"date":11,"day":5,"hours":16,"minutes":26,"month":11,"nanos":0,"seconds":11,"time":1449822371000,"timezoneOffset":-480,"year":115}
         * customerId : 459
         * freightPrice : 0
         * logoImage : shop/image/product/20150416/2015041617500000796233.png
         * orderDetailId : 1492
         * ordersId : 1180
         * ordersNo : 2015121116261111
         * productFullName : 耐克 运动T恤 [黑色]
         * productId : 1559
         * salesPrice : 150
         * shopInfoId : 275
         * shopName : 金利来专卖店
         */

        private List<ListEntity> list;

        public void setFinalAmount(String finalAmount) {
            this.finalAmount = finalAmount;
        }

        public void setOrdersId(String ordersId) {
            this.ordersId = ordersId;
        }

        public void setOrdersNo(String ordersNo) {
            this.ordersNo = ordersNo;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public String getFinalAmount() {
            return finalAmount;
        }

        public String getOrdersId() {
            return ordersId;
        }

        public String getOrdersNo() {
            return ordersNo;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            private int count;
            /**
             * date : 11
             * day : 5
             * hours : 16
             * minutes : 26
             * month : 11
             * nanos : 0
             * seconds : 11
             * time : 1449822371000
             * timezoneOffset : -480
             * year : 115
             */

            private CreateTimeEntity createTime;
            private int customerId;
            private float freightPrice;
            private String logoImage;
            private int orderDetailId;
            private int ordersId;
            private String ordersNo;
            private String productFullName;
            private int productId;
            private float salesPrice;
            private int shopInfoId;
            private String shopName;

            public void setCount(int count) {
                this.count = count;
            }

            public void setCreateTime(CreateTimeEntity createTime) {
                this.createTime = createTime;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public void setFreightPrice(float freightPrice) {
                this.freightPrice = freightPrice;
            }

            public void setLogoImage(String logoImage) {
                this.logoImage = logoImage;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }

            public void setOrdersId(int ordersId) {
                this.ordersId = ordersId;
            }

            public void setOrdersNo(String ordersNo) {
                this.ordersNo = ordersNo;
            }

            public void setProductFullName(String productFullName) {
                this.productFullName = productFullName;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public void setSalesPrice(float salesPrice) {
                this.salesPrice = salesPrice;
            }

            public void setShopInfoId(int shopInfoId) {
                this.shopInfoId = shopInfoId;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getCount() {
                return count;
            }

            public CreateTimeEntity getCreateTime() {
                return createTime;
            }

            public int getCustomerId() {
                return customerId;
            }

            public float getFreightPrice() {
                return freightPrice;
            }

            public String getLogoImage() {
                return logoImage;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public int getOrdersId() {
                return ordersId;
            }

            public String getOrdersNo() {
                return ordersNo;
            }

            public String getProductFullName() {
                return productFullName;
            }

            public int getProductId() {
                return productId;
            }

            public float getSalesPrice() {
                return salesPrice;
            }

            public int getShopInfoId() {
                return shopInfoId;
            }

            public String getShopName() {
                return shopName;
            }

            public static class CreateTimeEntity {
                private int date;
                private int day;
                private int hours;
                private int minutes;
                private int month;
                private int nanos;
                private int seconds;
                private long time;
                private int timezoneOffset;
                private int year;

                public void setDate(int date) {
                    this.date = date;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getDate() {
                    return date;
                }

                public int getDay() {
                    return day;
                }

                public int getHours() {
                    return hours;
                }

                public int getMinutes() {
                    return minutes;
                }

                public int getMonth() {
                    return month;
                }

                public int getNanos() {
                    return nanos;
                }

                public int getSeconds() {
                    return seconds;
                }

                public long getTime() {
                    return time;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public int getYear() {
                    return year;
                }
            }
        }
    }
}
