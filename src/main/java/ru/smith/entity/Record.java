package ru.smith.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "findMaxTimeStamp",
                query = "SELECT MAX(r.ts) FROM Record r"
        ),
        @NamedQuery(
                name = "selectRecordWithTSMore",
                query = "SELECT r FROM Record r WHERE r.ts > :hour"
        )
})

@Entity
@Table(name = "records")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ssoid")
    private String ssoid;

    @Column(name = "ts")
    private Instant ts;

    @Column(name = "grp")
    private String grp;

    @Column(name = "type")
    private String type;

    @Column(name = "subtype")
    private String subtype;

    @Column(name = "url")
    private String url;

    @Column(name = "orgid")
    private String orgid;

    @Column(name = "formid")
    private String formid;

    @Column(name = "code")
    private String code;

    @Column(name = "lpta")
    private String ltpa;

    @Column(name = "sudirresponse")
    private String sudirresponse;

    @Column(name = "ymdh")
    private LocalDateTime ymdh;



}
