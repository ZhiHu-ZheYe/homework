/**
 *Email()类实现Email属性的封装
 * 包括属性：
 * --messageID--email的ID，--from--发件地址，--to--收件人地址，--cc--抄送地址，---bcc--密送地址
 * --subject--邮件主题，--sentdata--发送的内容，--content--内容
 * --boolean类型：--replysign--     --html---  ---news--
 * --attachments--字符串数组
 */
package com.hzu.feirty.entity;
import java.util.ArrayList;

public class Email  {

    private static final long serialVersionUID = 1L;
    private String messageID;
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String sentdata;
    private String content;
    private String course;
	private boolean replysign;
    private boolean html;
    private boolean news;
    private ArrayList<String> attachments;
    private String attachmentname;
    public String getAttachmentname() {
		return attachmentname;
	}

	public void setAttachmentname(String attachmentname) {
		this.attachmentname = attachmentname;
	}

	private String charset;
    
    
    public Email(){
    	super();
    }
    
    public Email(String sub,String to,String content){
    	this.to = to;
    	this.subject = sub;
    	this.content = content;	   
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }
    
    public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

    public String getFrom() {
        return from;
    }

    public void setFrom(String string) {
        this.from = string;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSentdata() {
        return sentdata;
    }

    public void setSentdata(String sentdata) {
        this.sentdata = sentdata;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReplysign() {
        return replysign;
    }

    public void setReplysign(boolean replysign) {
        this.replysign = replysign;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public boolean isNews() {
        return news;
    }

    public void setNews(boolean news) {
        this.news = news;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
    public ArrayList<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<String> attachments) {
        this.attachments = attachments;
    }

}
