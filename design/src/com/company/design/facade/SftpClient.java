package com.company.design.facade;

public class SftpClient {
    //객체를 한번 감싸므로써 의존성을 sftpclint가 다 가져가고 새로운 인터페이스를 제공한다.

    private Ftp ftp;
    private Reader reader;
    private Writer writer;

    public SftpClient(Ftp ftp,Reader reader,Writer writer){
        this.ftp=ftp;
        this.reader=reader;
        this.writer=writer;
    }

    public SftpClient(String host,int port, String path,String fileName){
        this.ftp=new Ftp(host,port,path);
        this.reader=new Reader(fileName);
        this.writer=new Writer(fileName);
    }

    public void connect(){
        ftp.connect();
        ftp.moveDirectory();
        writer.fileConnect();
        reader.fileConnect();
    }

    public void disConnect(){
        reader.fileDisconnect();
        writer.fileDisConnect();
        ftp.disConnect();
    }

    public void read(){
        reader.fileRead();
    }

    public void write(){
        writer.write();
    }

}