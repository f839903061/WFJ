package hy.zc.wfj.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feng on 2015/11/10.
 */
public class CategroyJsonObject implements Serializable{

    private boolean Status;

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
        private String categoryDescription;
        private String categoryImage;
        private int industrySpecific;
        private int isRecommend;
        private int isShow;
        private int level;
        private String loadType;
        private int parentId;
        private int productTypeId;
        private String sortCode;
        private String sortName;
        private List<?> brandList;


        private List<ChildProductTypeEntity> childProductType;

        public void setCategoryDescription(String categoryDescription) {
            this.categoryDescription = categoryDescription;
        }

        public void setCategoryImage(String categoryImage) {
            this.categoryImage = categoryImage;
        }

        public void setIndustrySpecific(int industrySpecific) {
            this.industrySpecific = industrySpecific;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setLoadType(String loadType) {
            this.loadType = loadType;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public void setProductTypeId(int productTypeId) {
            this.productTypeId = productTypeId;
        }

        public void setSortCode(String sortCode) {
            this.sortCode = sortCode;
        }

        public void setSortName(String sortName) {
            this.sortName = sortName;
        }

        public void setBrandList(List<?> brandList) {
            this.brandList = brandList;
        }

        public void setChildProductType(List<ChildProductTypeEntity> childProductType) {
            this.childProductType = childProductType;
        }

        public String getCategoryDescription() {
            return categoryDescription;
        }

        public String getCategoryImage() {
            return categoryImage;
        }

        public int getIndustrySpecific() {
            return industrySpecific;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public int getIsShow() {
            return isShow;
        }

        public int getLevel() {
            return level;
        }

        public String getLoadType() {
            return loadType;
        }

        public int getParentId() {
            return parentId;
        }

        public int getProductTypeId() {
            return productTypeId;
        }

        public String getSortCode() {
            return sortCode;
        }

        public String getSortName() {
            return sortName;
        }

        public List<?> getBrandList() {
            return brandList;
        }

        public List<ChildProductTypeEntity> getChildProductType() {
            return childProductType;
        }

        public static class ChildProductTypeEntity implements Serializable{
            private String categoryDescription;
            private String categoryImage;
            private int industrySpecific;
            private int isRecommend;
            private int isShow;
            private int level;
            private String loadType;
            private int parentId;
            private int productTypeId;
            private String sortCode;
            private String sortName;
            private List<?> brandList;
            /**
             * brandList : []
             * categoryDescription : 毛呢大衣
             * categoryImage :
             * childProductType : []
             * industrySpecific : 0
             * isRecommend : 0
             * isShow : 1
             * level : 3
             * loadType : 1
             * parentId : 4
             * productTypeId : 16
             * sortCode : 1
             * sortName : 毛呢大衣
             */

            private List<ChildProductTypeEntity> childProductType;

            public void setCategoryDescription(String categoryDescription) {
                this.categoryDescription = categoryDescription;
            }

            public void setCategoryImage(String categoryImage) {
                this.categoryImage = categoryImage;
            }

            public void setIndustrySpecific(int industrySpecific) {
                this.industrySpecific = industrySpecific;
            }

            public void setIsRecommend(int isRecommend) {
                this.isRecommend = isRecommend;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public void setLoadType(String loadType) {
                this.loadType = loadType;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public void setProductTypeId(int productTypeId) {
                this.productTypeId = productTypeId;
            }

            public void setSortCode(String sortCode) {
                this.sortCode = sortCode;
            }

            public void setSortName(String sortName) {
                this.sortName = sortName;
            }

            public void setBrandList(List<?> brandList) {
                this.brandList = brandList;
            }

            public void setChildProductType(List<ChildProductTypeEntity> childProductType) {
                this.childProductType = childProductType;
            }

            public String getCategoryDescription() {
                return categoryDescription;
            }

            public String getCategoryImage() {
                return categoryImage;
            }

            public int getIndustrySpecific() {
                return industrySpecific;
            }

            public int getIsRecommend() {
                return isRecommend;
            }

            public int getIsShow() {
                return isShow;
            }

            public int getLevel() {
                return level;
            }

            public String getLoadType() {
                return loadType;
            }

            public int getParentId() {
                return parentId;
            }

            public int getProductTypeId() {
                return productTypeId;
            }

            public String getSortCode() {
                return sortCode;
            }

            public String getSortName() {
                return sortName;
            }

            public List<?> getBrandList() {
                return brandList;
            }

            public List<ChildProductTypeEntity> getChildProductType() {
                return childProductType;
            }

            public static class ChildProductTypeEntity2 implements Serializable{
                private String categoryDescription;
                private String categoryImage;
                private int industrySpecific;
                private int isRecommend;
                private int isShow;
                private int level;
                private String loadType;
                private int parentId;
                private int productTypeId;
                private String sortCode;
                private String sortName;
                private List<?> brandList;
                private List<?> childProductType;

                public void setCategoryDescription(String categoryDescription) {
                    this.categoryDescription = categoryDescription;
                }

                public void setCategoryImage(String categoryImage) {
                    this.categoryImage = categoryImage;
                }

                public void setIndustrySpecific(int industrySpecific) {
                    this.industrySpecific = industrySpecific;
                }

                public void setIsRecommend(int isRecommend) {
                    this.isRecommend = isRecommend;
                }

                public void setIsShow(int isShow) {
                    this.isShow = isShow;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public void setLoadType(String loadType) {
                    this.loadType = loadType;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public void setProductTypeId(int productTypeId) {
                    this.productTypeId = productTypeId;
                }

                public void setSortCode(String sortCode) {
                    this.sortCode = sortCode;
                }

                public void setSortName(String sortName) {
                    this.sortName = sortName;
                }

                public void setBrandList(List<?> brandList) {
                    this.brandList = brandList;
                }

                public void setChildProductType(List<?> childProductType) {
                    this.childProductType = childProductType;
                }

                public String getCategoryDescription() {
                    return categoryDescription;
                }

                public String getCategoryImage() {
                    return categoryImage;
                }

                public int getIndustrySpecific() {
                    return industrySpecific;
                }

                public int getIsRecommend() {
                    return isRecommend;
                }

                public int getIsShow() {
                    return isShow;
                }

                public int getLevel() {
                    return level;
                }

                public String getLoadType() {
                    return loadType;
                }

                public int getParentId() {
                    return parentId;
                }

                public int getProductTypeId() {
                    return productTypeId;
                }

                public String getSortCode() {
                    return sortCode;
                }

                public String getSortName() {
                    return sortName;
                }

                public List<?> getBrandList() {
                    return brandList;
                }

                public List<?> getChildProductType() {
                    return childProductType;
                }
            }
        }
    }
}
