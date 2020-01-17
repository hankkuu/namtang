package kosta.namtang.talkbook;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class bookApiDomain{
    private String text;
    private String catgNum;
    private Long catgNumId;
}
