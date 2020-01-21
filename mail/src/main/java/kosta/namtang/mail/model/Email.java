package kosta.namtang.mail.model;

import lombok.Data;

import java.io.File;

@Data
public class Email {
    @javax.validation.constraints.Email
    public String sendTo;
    public String name;
    public String subject;
    public String content;
    public boolean isAttachedFile;
    public File file;
}
