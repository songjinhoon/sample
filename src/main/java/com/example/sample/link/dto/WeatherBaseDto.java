package com.example.sample.link.dto;

import lombok.Getter;

@Getter
public class WeatherBaseDto {

    private Response response;

    @Getter
    public static class Response {


        private Header header;

        private Body body;

        @Getter
        public static class Header {

            private String resultCode;

            private String resultMsg;

        }

        @Getter
        public static class Body {

            private String dataType;

            private Data items;

            private int pageNo;

            private int numOfRows;

            private int totalCount;

            @Getter
            public static class Data {

                private Object item;

            }

        }

    }

    public boolean isError() {
        return !this.response.header.resultCode.equals("00");
    }

}
