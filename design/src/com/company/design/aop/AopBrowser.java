package com.company.design.aop;

import com.company.design.proxy.HTML;
import com.company.design.proxy.IBrowser;

public class AopBrowser implements IBrowser {

    private String url;
    private HTML html;

    //functional interface의 종류 Runnable
    //argument 없고 반환 값 없는 run() 메소드를 가지고 있음.
    private Runnable before;
    private Runnable after;

    public AopBrowser(String url,Runnable before,Runnable after){
        this.url=url;
        this.before=before;
        this.after=after;
    }

    @Override
    public HTML show() {
        //AOP 구현 : before와 after를 통해 중간 로직 전후로 method 실행 시간 측정
        before.run();

        if(html==null){
            this.html=new HTML(url);
            System.out.println("AopBrowser html loading from : "+url);
            try {
                Thread.sleep(1500); //method 속도가 빠를 수 있기 때문에 1.5초 기다린다.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        after.run();

        System.out.println("AopBrowser html cache : "+url);
        return html;
    }
}
