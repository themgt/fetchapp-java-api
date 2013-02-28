package com.nicohuysamen.fetchapp.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "sku")
    private String sku;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "price")
    private float price;

    @XmlElement(name = "currency")
    private String currency;

    @XmlElement(name = "order_count")
    private int orderCount;

    @XmlElement(name = "download_count")
    private int downloadCount;

    @XmlElement(name = "paypal_add_to_cart_link")
    private String paypalAddToCartLink;

    @XmlElement(name = "paypal_buy_now_link")
    private String paypalBuyNowLink;

    @XmlElement(name = "paypal_view_cart_link")
    private String paypalViewCartLink;

    @XmlElement(name = "created_at")
    private Date createdAt;

    @XmlElement(name = "files_uri")
    private String filesUri;

    @XmlElement(name = "downloads_uri")
    private String downloadsUri;

    @XmlElementWrapper(name = "files")
    @XmlElement(name = "id")
    private List<String> files;

    public String getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(final String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(final float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public String getPaypalAddToCartLink() {
        return paypalAddToCartLink;
    }

    public String getPaypalBuyNowLink() {
        return paypalBuyNowLink;
    }

    public String getPaypalViewCartLink() {
        return paypalViewCartLink;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getFilesUri() {
        return filesUri;
    }

    public String getDownloadsUri() {
        return downloadsUri;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(final List<String> files) {
        if (this.files == null) {
            this.files = new ArrayList<String>();
        }

        this.files.addAll(files);
    }

    public void addFile(final String file) {
        if (this.files == null) {
            this.files = new ArrayList<String>();
        }

        files.add(file);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", orderCount=" + orderCount +
                ", downloadCount=" + downloadCount +
                ", paypalAddToCartLink='" + paypalAddToCartLink + '\'' +
                ", paypalBuyNowLink='" + paypalBuyNowLink + '\'' +
                ", paypalViewCartLink='" + paypalViewCartLink + '\'' +
                ", createdAt=" + createdAt +
                ", filesUri='" + filesUri + '\'' +
                ", downloadsUri='" + downloadsUri + '\'' +
                ", files=" + files +
                '}';
    }
}
