package com.larry.present.logo.dto;

/**
 * Created by Laiyin on 2017/8/3.
 *
 * 检查系统是否需要更新的结果回调数据
 */
public class IsNeedToUpdateDto {

    /**
     * 版本id
     */
    private int id;

    /**
     * 版本平台：android
     */
    private String platform;

    /**
     * 版本名称
     */
    private String versionName;

    /**
     * 版本号
     */
    private int versionCode;

    /**
     * 是否有新版本 有为1 没有为0
     */
    private int hasNewVersion;

    /**
     * 是否需要强制升级 需要为1 不需要为0
     * 当客户端的版本无法在服务器所存的几个版本中查找时则需要强制更新
     */
    private int must;

    /**
     * 升级信息
     */
    private String upGradeInfo;

    /**
     * app最新版本地址
     */
    private String updateUrl;

    /**
     * 版本创建日期
     */
    private String createDate;

    public String getUpGradeInfo() {
        return upGradeInfo;
    }

    public void setUpGradeInfo(String upGradeInfo) {
        this.upGradeInfo = upGradeInfo;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getHasNewVersion() {
        return hasNewVersion;
    }

    public void setHasNewVersion(int hasNewVersion) {
        this.hasNewVersion = hasNewVersion;
    }

    public int getMust() {
        return must;
    }

    public void setMust(int must) {
        this.must = must;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
