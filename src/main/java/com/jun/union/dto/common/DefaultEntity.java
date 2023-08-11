package com.jun.union.dto.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DefaultEntity extends CommonDTO{


    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDt;

    @Column(updatable = false)
    protected String regDay;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDt;

    @PrePersist
    public void prePersist() {
        regDt = regDt != null ? regDt : LocalDateTime.now();
        regDay = StringUtils.isEmpty(regDay) ? regDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : regDay;
    }

    @PreUpdate
    public void preUpdate() {
        modDt = modDt != null ? modDt : LocalDateTime.now();
    }
}
