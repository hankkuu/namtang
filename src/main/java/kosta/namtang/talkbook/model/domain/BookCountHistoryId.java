package kosta.namtang.talkbook.model.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookCountHistoryId implements Serializable {
    private Long bookIdx;
    private String History;



}