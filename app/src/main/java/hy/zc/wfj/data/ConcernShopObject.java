package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/12/11.
 */
public class ConcernShopObject implements Serializable{


    private static final long serialVersionUID = -9206447284273490700L;

    /**
     * Status : true
     * Data : [{"IDCards":"","IDCardsImage":"","address":"江西省南昌市青山湖区青山路","addressForInvoice":"吉安市河东街道10号","applyTime":null,"areaCounty":"","bankAccountNumber":"","bannerUrl":"shop/image/shopInfo/20150406/2015040618110001557356.jpg","businessHoursEnd":"20450314","businessHoursStart":"20150315","businessLicense":"shop/image/shopInfo/20150325/2015032514080000143377.png","businessType":0,"city":"","commission":"5","companyCertification":"","companyDocuments":"shop/image/shopInfo/20150325/2015032514080000412084.png","companyName":"江西东方科技有限公司","companyNameForInvoice":"","companyRegistered":"","customerId":413,"customerName":"1039883454_mpde","description":"","email":"","invoiceCheckStatus":0,"invoiceInfo":"","invoiceType":0,"isClose":0,"isPass":3,"isRecommend":0,"isVip":0,"legalOwner":"","logoUrl":"shop/image/shopInfo/20150325/2015032514420002730396.png","mainProduct":"电器","marketBrand":"","marketBrandUrl":"shop/image/shopInfo/20150325/2015032514070005652551.png","minAmount":50,"openingBank":"","passTime":{"date":17,"day":5,"hours":12,"minutes":2,"month":3,"nanos":0,"seconds":52,"time":1429243372000,"timezoneOffset":-480,"year":115},"phone":"0796-8101997","phoneForInvoice":"","phoneShowStatus":0,"postCode":"330117","postage":3,"productInfoList":[],"productInfoMap":[],"qrCode":"shop/image/shopInfo/20150417/2015041712020005220066.png","regionLocation":"","shopCategoryId":8,"shopInfoCheckSatus":2,"shopInfoId":274,"shopName":"飞利浦旗舰店","synopsis":"我店产品全部来自正规的渠道，以最直接有效的方式送达最终端消费者手里，将尽最大可能让利给大家，比专柜相对来说便宜许多。\r\n欢迎光临本店，本店新开张，诚信经营，只赚信誉不赚钱，谢谢。\r\n电话;15079637295\r\n地址：青原区新桥商住楼2单元402室","tag":"","taxRegistrationDocuments":"shop/image/shopInfo/20150325/2015032514080000963829.png","taxpayerNumber":"","templateSet":3,"verifier":"sunping"},{"IDCards":"","IDCardsImage":"","address":"吉安市井冈山大道100号","addressForInvoice":"吉安市井冈山大道100号","applyTime":null,"areaCounty":"","bankAccountNumber":"","bannerUrl":"shop/image/shopInfo/20150408/2015040811340005124938.jpg","businessHoursEnd":"22","businessHoursStart":"08","businessLicense":"shop/image/shopInfo/20150326/2015032610160000519560.png","businessType":0,"city":"","commission":"5","companyCertification":"","companyDocuments":"shop/image/shopInfo/20150326/2015032610160005589949.png","companyName":"OPPO专卖店","companyNameForInvoice":"","companyRegistered":"","customerId":411,"customerName":"37184893_siwq","description":"简介：东来顺百十年薪火相传，老字号跨世纪推陈出新，中华老字号\u201c东来顺\u201d，是一个具有百年历史的中华老字号，始创于1903年，其传统\u201c涮羊肉\u201d以独特的风味、纯正的品质、精湛的技艺而驰名海内外，素有\u201c食之精粹、国之瑰宝\u201d之美誉。除\u201d涮\u201c之外，东来顺的...","email":"","invoiceCheckStatus":0,"invoiceInfo":"","invoiceType":0,"isClose":0,"isPass":3,"isRecommend":0,"isVip":0,"legalOwner":"","logoUrl":"shop/image/shopInfo/20150401/2015040116150005277811.jpg","mainProduct":"服装鞋靴","marketBrand":"","marketBrandUrl":"shop/image/shopInfo/20150401/2015040116240002658139.jpg","minAmount":50,"openingBank":"","passTime":{"date":18,"day":6,"hours":16,"minutes":21,"month":3,"nanos":0,"seconds":25,"time":1429345285000,"timezoneOffset":-480,"year":115},"phone":"0796-8105246","phoneForInvoice":"","phoneShowStatus":0,"postCode":"343000","postage":3,"productInfoList":[],"productInfoMap":[],"qrCode":"shop/image/shopInfo/20150418/2015041816210002587369.png","regionLocation":"","shopCategoryId":2,"shopInfoCheckSatus":2,"shopInfoId":272,"shopName":"OPPO专卖店","synopsis":"OPPO专卖店","tag":"","taxRegistrationDocuments":"shop/image/shopInfo/20150326/2015032610170004391877.png","taxpayerNumber":"","templateSet":3,"verifier":"pengtian"}]
     */

    private boolean Status;
    /**
     * IDCards :
     * IDCardsImage :
     * address : 江西省南昌市青山湖区青山路
     * addressForInvoice : 吉安市河东街道10号
     * applyTime : null
     * areaCounty :
     * bankAccountNumber :
     * bannerUrl : shop/image/shopInfo/20150406/2015040618110001557356.jpg
     * businessHoursEnd : 20450314
     * businessHoursStart : 20150315
     * businessLicense : shop/image/shopInfo/20150325/2015032514080000143377.png
     * businessType : 0
     * city :
     * commission : 5
     * companyCertification :
     * companyDocuments : shop/image/shopInfo/20150325/2015032514080000412084.png
     * companyName : 江西东方科技有限公司
     * companyNameForInvoice :
     * companyRegistered :
     * customerId : 413
     * customerName : 1039883454_mpde
     * description :
     * email :
     * invoiceCheckStatus : 0
     * invoiceInfo :
     * invoiceType : 0
     * isClose : 0
     * isPass : 3
     * isRecommend : 0
     * isVip : 0
     * legalOwner :
     * logoUrl : shop/image/shopInfo/20150325/2015032514420002730396.png
     * mainProduct : 电器
     * marketBrand :
     * marketBrandUrl : shop/image/shopInfo/20150325/2015032514070005652551.png
     * minAmount : 50
     * openingBank :
     * passTime : {"date":17,"day":5,"hours":12,"minutes":2,"month":3,"nanos":0,"seconds":52,"time":1429243372000,"timezoneOffset":-480,"year":115}
     * phone : 0796-8101997
     * phoneForInvoice :
     * phoneShowStatus : 0
     * postCode : 330117
     * postage : 3
     * productInfoList : []
     * productInfoMap : []
     * qrCode : shop/image/shopInfo/20150417/2015041712020005220066.png
     * regionLocation :
     * shopCategoryId : 8
     * shopInfoCheckSatus : 2
     * shopInfoId : 274
     * shopName : 飞利浦旗舰店
     * synopsis : 我店产品全部来自正规的渠道，以最直接有效的方式送达最终端消费者手里，将尽最大可能让利给大家，比专柜相对来说便宜许多。
     欢迎光临本店，本店新开张，诚信经营，只赚信誉不赚钱，谢谢。
     电话;15079637295
     地址：青原区新桥商住楼2单元402室
     * tag :
     * taxRegistrationDocuments : shop/image/shopInfo/20150325/2015032514080000963829.png
     * taxpayerNumber :
     * templateSet : 3
     * verifier : sunping
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

        private static final long serialVersionUID = 5260782259266327395L;

        private String IDCards;
        private String IDCardsImage;
        private String address;
        private String addressForInvoice;
        private Object applyTime;
        private String areaCounty;
        private String bankAccountNumber;
        private String bannerUrl;
        private String businessHoursEnd;
        private String businessHoursStart;
        private String businessLicense;
        private int businessType;
        private String city;
        private String commission;
        private String companyCertification;
        private String companyDocuments;
        private String companyName;
        private String companyNameForInvoice;
        private String companyRegistered;
        private int customerId;
        private String customerName;
        private String description;
        private String email;
        private int invoiceCheckStatus;
        private String invoiceInfo;
        private int invoiceType;
        private int isClose;
        private int isPass;
        private int isRecommend;
        private int isVip;
        private String legalOwner;
        private String logoUrl;
        private String mainProduct;
        private String marketBrand;
        private String marketBrandUrl;
        private int minAmount;
        private String openingBank;
        /**
         * date : 17
         * day : 5
         * hours : 12
         * minutes : 2
         * month : 3
         * nanos : 0
         * seconds : 52
         * time : 1429243372000
         * timezoneOffset : -480
         * year : 115
         */

        private PassTimeEntity passTime;
        private String phone;
        private String phoneForInvoice;
        private int phoneShowStatus;
        private String postCode;
        private int postage;
        private String qrCode;
        private String regionLocation;
        private int shopCategoryId;
        private int shopInfoCheckSatus;
        private int shopInfoId;
        private String shopName;
        private String synopsis;
        private String tag;
        private String taxRegistrationDocuments;
        private String taxpayerNumber;
        private int templateSet;
        private String verifier;
        private List<?> productInfoList;
        private List<?> productInfoMap;

        public void setIDCards(String IDCards) {
            this.IDCards = IDCards;
        }

        public void setIDCardsImage(String IDCardsImage) {
            this.IDCardsImage = IDCardsImage;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setAddressForInvoice(String addressForInvoice) {
            this.addressForInvoice = addressForInvoice;
        }

        public void setApplyTime(Object applyTime) {
            this.applyTime = applyTime;
        }

        public void setAreaCounty(String areaCounty) {
            this.areaCounty = areaCounty;
        }

        public void setBankAccountNumber(String bankAccountNumber) {
            this.bankAccountNumber = bankAccountNumber;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public void setBusinessHoursEnd(String businessHoursEnd) {
            this.businessHoursEnd = businessHoursEnd;
        }

        public void setBusinessHoursStart(String businessHoursStart) {
            this.businessHoursStart = businessHoursStart;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public void setCompanyCertification(String companyCertification) {
            this.companyCertification = companyCertification;
        }

        public void setCompanyDocuments(String companyDocuments) {
            this.companyDocuments = companyDocuments;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public void setCompanyNameForInvoice(String companyNameForInvoice) {
            this.companyNameForInvoice = companyNameForInvoice;
        }

        public void setCompanyRegistered(String companyRegistered) {
            this.companyRegistered = companyRegistered;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setInvoiceCheckStatus(int invoiceCheckStatus) {
            this.invoiceCheckStatus = invoiceCheckStatus;
        }

        public void setInvoiceInfo(String invoiceInfo) {
            this.invoiceInfo = invoiceInfo;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public void setIsClose(int isClose) {
            this.isClose = isClose;
        }

        public void setIsPass(int isPass) {
            this.isPass = isPass;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public void setLegalOwner(String legalOwner) {
            this.legalOwner = legalOwner;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public void setMainProduct(String mainProduct) {
            this.mainProduct = mainProduct;
        }

        public void setMarketBrand(String marketBrand) {
            this.marketBrand = marketBrand;
        }

        public void setMarketBrandUrl(String marketBrandUrl) {
            this.marketBrandUrl = marketBrandUrl;
        }

        public void setMinAmount(int minAmount) {
            this.minAmount = minAmount;
        }

        public void setOpeningBank(String openingBank) {
            this.openingBank = openingBank;
        }

        public void setPassTime(PassTimeEntity passTime) {
            this.passTime = passTime;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setPhoneForInvoice(String phoneForInvoice) {
            this.phoneForInvoice = phoneForInvoice;
        }

        public void setPhoneShowStatus(int phoneShowStatus) {
            this.phoneShowStatus = phoneShowStatus;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public void setPostage(int postage) {
            this.postage = postage;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public void setRegionLocation(String regionLocation) {
            this.regionLocation = regionLocation;
        }

        public void setShopCategoryId(int shopCategoryId) {
            this.shopCategoryId = shopCategoryId;
        }

        public void setShopInfoCheckSatus(int shopInfoCheckSatus) {
            this.shopInfoCheckSatus = shopInfoCheckSatus;
        }

        public void setShopInfoId(int shopInfoId) {
            this.shopInfoId = shopInfoId;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setTaxRegistrationDocuments(String taxRegistrationDocuments) {
            this.taxRegistrationDocuments = taxRegistrationDocuments;
        }

        public void setTaxpayerNumber(String taxpayerNumber) {
            this.taxpayerNumber = taxpayerNumber;
        }

        public void setTemplateSet(int templateSet) {
            this.templateSet = templateSet;
        }

        public void setVerifier(String verifier) {
            this.verifier = verifier;
        }

        public void setProductInfoList(List<?> productInfoList) {
            this.productInfoList = productInfoList;
        }

        public void setProductInfoMap(List<?> productInfoMap) {
            this.productInfoMap = productInfoMap;
        }

        public String getIDCards() {
            return IDCards;
        }

        public String getIDCardsImage() {
            return IDCardsImage;
        }

        public String getAddress() {
            return address;
        }

        public String getAddressForInvoice() {
            return addressForInvoice;
        }

        public Object getApplyTime() {
            return applyTime;
        }

        public String getAreaCounty() {
            return areaCounty;
        }

        public String getBankAccountNumber() {
            return bankAccountNumber;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public String getBusinessHoursEnd() {
            return businessHoursEnd;
        }

        public String getBusinessHoursStart() {
            return businessHoursStart;
        }

        public String getBusinessLicense() {
            return businessLicense;
        }

        public int getBusinessType() {
            return businessType;
        }

        public String getCity() {
            return city;
        }

        public String getCommission() {
            return commission;
        }

        public String getCompanyCertification() {
            return companyCertification;
        }

        public String getCompanyDocuments() {
            return companyDocuments;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getCompanyNameForInvoice() {
            return companyNameForInvoice;
        }

        public String getCompanyRegistered() {
            return companyRegistered;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getDescription() {
            return description;
        }

        public String getEmail() {
            return email;
        }

        public int getInvoiceCheckStatus() {
            return invoiceCheckStatus;
        }

        public String getInvoiceInfo() {
            return invoiceInfo;
        }

        public int getInvoiceType() {
            return invoiceType;
        }

        public int getIsClose() {
            return isClose;
        }

        public int getIsPass() {
            return isPass;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public int getIsVip() {
            return isVip;
        }

        public String getLegalOwner() {
            return legalOwner;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public String getMainProduct() {
            return mainProduct;
        }

        public String getMarketBrand() {
            return marketBrand;
        }

        public String getMarketBrandUrl() {
            return marketBrandUrl;
        }

        public int getMinAmount() {
            return minAmount;
        }

        public String getOpeningBank() {
            return openingBank;
        }

        public PassTimeEntity getPassTime() {
            return passTime;
        }

        public String getPhone() {
            return phone;
        }

        public String getPhoneForInvoice() {
            return phoneForInvoice;
        }

        public int getPhoneShowStatus() {
            return phoneShowStatus;
        }

        public String getPostCode() {
            return postCode;
        }

        public int getPostage() {
            return postage;
        }

        public String getQrCode() {
            return qrCode;
        }

        public String getRegionLocation() {
            return regionLocation;
        }

        public int getShopCategoryId() {
            return shopCategoryId;
        }

        public int getShopInfoCheckSatus() {
            return shopInfoCheckSatus;
        }

        public int getShopInfoId() {
            return shopInfoId;
        }

        public String getShopName() {
            return shopName;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public String getTag() {
            return tag;
        }

        public String getTaxRegistrationDocuments() {
            return taxRegistrationDocuments;
        }

        public String getTaxpayerNumber() {
            return taxpayerNumber;
        }

        public int getTemplateSet() {
            return templateSet;
        }

        public String getVerifier() {
            return verifier;
        }

        public List<?> getProductInfoList() {
            return productInfoList;
        }

        public List<?> getProductInfoMap() {
            return productInfoMap;
        }

        public static class PassTimeEntity {
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
