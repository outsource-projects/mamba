package com.renguangli.mamba.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Product
 *
 * @author renguangli 2018/1/21 0:20
 * @since JDK 1.8
 */
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer xid;

    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;

    private String pid;

    private String name;

    private String brand;

    @Column(length = 1024, name = "TopBrand")
    private String topBrand;

    private String price;

    private String originalPrice;

    private Integer salesCount;

    private Integer collectionCount;

    private String stock;

    private double score;

    private Integer commentsCount;

    private String sid;

    private String keyword;

    private String keywordRank;

    private String shopName;

    @Column(length = 1024)
    private String productUrl;

    private String shopUrl;

    public Product() {}

    public Product(String url, Date time, String pid, String name, String brand, String topBrand, String price, String originalPrice, Integer salesCount, Integer collectionCount, String stock, double score, Integer commentsCount, String sid, String keyword, String keywordRank, String shopName, String productUrl, String shopUrl) {
        this.url = url;
        this.time = time;
        this.pid = pid;
        this.name = name;
        this.brand = brand;
        this.topBrand = topBrand;
        this.price = price;
        this.originalPrice = originalPrice;
        this.salesCount = salesCount;
        this.collectionCount = collectionCount;
        this.stock = stock;
        this.score = score;
        this.commentsCount = commentsCount;
        this.sid = sid;
        this.keyword = keyword;
        this.keywordRank = keywordRank;
        this.shopName = shopName;
        this.productUrl = productUrl;
        this.shopUrl = shopUrl;
    }

    public Integer getXId() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTopBrand() {
        return topBrand;
    }

    public void setTopBrand(String topBrand) {
        this.topBrand = topBrand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeywordRank() {
        return keywordRank;
    }

    public void setKeywordRand(String keywordRank) {
        this.keywordRank = keywordRank;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "xid=" + xid +
                ", url='" + url + '\'' +
                ", time=" + time +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", topBrand='" + topBrand + '\'' +
                ", price='" + price + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", salesCount=" + salesCount +
                ", collectionCount=" + collectionCount +
                ", stock='" + stock + '\'' +
                ", score=" + score +
                ", commentsCount=" + commentsCount +
                ", sid='" + sid + '\'' +
                ", keyword='" + keyword + '\'' +
                ", keywordRand='" + keywordRank + '\'' +
                ", shopName='" + shopName + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", shopUrl='" + shopUrl + '\'' +
                '}';
    }
}
