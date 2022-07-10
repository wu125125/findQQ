package com.guo.entity;

import java.util.List;

public class Parent {

        private String sid;
        private String prism_version;
        private int prism_wnum;
        private List<Prism_wordsInfo> prism_wordsInfo;
        private int height;
        private int width;
        private int orgHeight;
        private int orgWidth;
        private String content;
        public void setSid(String sid) {
            this.sid = sid;
        }
        public String getSid() {
            return sid;
        }

        public void setPrism_version(String prism_version) {
            this.prism_version = prism_version;
        }
        public String getPrism_version() {
            return prism_version;
        }

        public void setPrism_wnum(int prism_wnum) {
            this.prism_wnum = prism_wnum;
        }
        public int getPrism_wnum() {
            return prism_wnum;
        }

        public void setPrism_wordsInfo(List<Prism_wordsInfo> prism_wordsInfo) {
            this.prism_wordsInfo = prism_wordsInfo;
        }
        public List<Prism_wordsInfo> getPrism_wordsInfo() {
            return prism_wordsInfo;
        }

        public void setHeight(int height) {
            this.height = height;
        }
        public int getHeight() {
            return height;
        }

        public void setWidth(int width) {
            this.width = width;
        }
        public int getWidth() {
            return width;
        }

        public void setOrgHeight(int orgHeight) {
            this.orgHeight = orgHeight;
        }
        public int getOrgHeight() {
            return orgHeight;
        }

        public void setOrgWidth(int orgWidth) {
            this.orgWidth = orgWidth;
        }
        public int getOrgWidth() {
            return orgWidth;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

    @Override
    public String toString() {
        return "Parent{" +
                "sid='" + sid + '\'' +
                ", prism_version='" + prism_version + '\'' +
                ", prism_wnum=" + prism_wnum +
                ", prism_wordsInfo=" + prism_wordsInfo +
                ", height=" + height +
                ", width=" + width +
                ", orgHeight=" + orgHeight +
                ", orgWidth=" + orgWidth +
                ", content='" + content + '\'' +
                '}';
    }
}
