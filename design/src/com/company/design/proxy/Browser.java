package com.company.design.proxy;

public class Browser implements IBrowser{
    private String url;

    public Browser(String url){
        this.url=url;
    }

    @Override
    public HTML show() {
        System.out.println("browser loading html from : "+url);
        return new HTML(url);
    }
}
