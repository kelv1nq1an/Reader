package com.fierydragon.chain.reader.data;

public class ArticleData {
    private static final String TAG = "ArticleData";

    public ArticleData(String articleCategory, String articleName, String articleContent) {
        setArticleCategory(articleCategory);
        setArticleName(articleName);
        setArticleContent(articleContent);
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleImageUrl() {
        return articleImageUrl;
    }

    public void setArticleImageUrl(String articleImageUrl) {
        this.articleImageUrl = articleImageUrl;
    }

    private String articleCategory;
    private String articleName;
    private String articleContent;
    private String articleImageUrl;
}
