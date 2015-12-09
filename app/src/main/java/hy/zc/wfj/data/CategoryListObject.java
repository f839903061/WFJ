package hy.zc.wfj.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by feng on 2015/10/28.
 */
public class CategoryListObject {
    private String name;
    private float price;
    private String image_path;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets image path.
     *
     * @return the image path
     */
    public String getImage_path() {
        return image_path;
    }

    /**
     * Sets image path.
     *
     * @param image_path the image path
     */
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    /**
     * @return all attribute to String
     */
    @Override
    public String toString() {
        return "CategoryListObject{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image_path='" + image_path + '\'' +
                '}';
    }

    public CategoryListObject() {
    }

    public CategoryListObject(String name, int price, String image_path) {
        this.name = name;
        this.price = price;
        this.image_path = image_path;
    }
}
