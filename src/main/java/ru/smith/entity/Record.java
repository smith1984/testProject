package ru.smith.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "maxTimeStamp",
                query = "SELECT MAX(r.ts) FROM Record r"
        ),
        @NamedQuery(
                name = "recordsWithTSMoreX",
                query = "SELECT r.ssoid, r.formid FROM Record r WHERE r.ts > :hour"
        ),
        @NamedQuery(
                name = "ratingForms",
                query = "SELECT COUNT(r.formid), r.formid, r.orgid FROM Record r" +
                        " GROUP BY r.orgid, r.formid ORDER BY COUNT(r.formid) DESC"
        ),
        @NamedQuery(
                name = "countUseForm",
                query = "SELECT COUNT(r.formid) FROM Record r"
        )
})
@NamedNativeQuery(
        name = "stopUseForm",
        query = "select r.ts, r.ssoid, r.formid, r.type, r.subtype from records r " +
                "join (select max(ts) ts, ssoid, formid from records group by " +
                "ssoid, formid) AS rec on r.ts = rec.ts and r.ssoid = rec.ssoid" +
                " and r.formid = rec.formid where r.formid is not null" +
                " and r.ssoid <> '' and r.ssoid <> 'Unauthorized' " +
                "and r.subtype <> '' and subtype" +
                " not in ('send', 'sent', 'done', 'success') order by r.ts"
)


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
