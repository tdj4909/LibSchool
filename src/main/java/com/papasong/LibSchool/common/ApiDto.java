package com.papasong.LibSchool.common;

import lombok.Data;

import java.util.List;

@Data
public class ApiDto {

    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
    public static class Header{
        private String resultCode;
        private String resultMsg;
    }

    @Data
    public static class Body{
        private Items items;
        private String numOfRows;
        private String pageNo;
        private String totalCount;
    }

    @Data
    public static class Items{
        private List<Item> item;
    }

    @Data
    public static class Item{
        private String schNm;
        private String schTp;
        private String instDt;
        private String schRoadAddr;
        private String schLatPos;
        private String schLotPos;
        private String fcltyNm;
        private String lclasNm;
        private String fcltyRoadNmAddr;
        private String description;
        private String subDescription;
        private String homepageUrl;
        private String telNo;
        private String openInstNm;
        private String pvsnInstNm;
        private String dataStdDate;
        private String fcltyLatPos;
        private String fcltyLotPos;
        private String dist;
    }
}
