/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.domain;

import edu.iit.sat.itmd4515.wzhang87.service.api.LocalDateTimeXmlAdapter;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author mrslo
 */
@XmlAccessorType(XmlAccessType.FIELD)

@MappedSuperclass
public class AbstractIdentifiedEntity {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Version
    private Long version;
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime lastUpdatedTimeStamp;
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime createdTimeStamp;

    @PrePersist
    private void prePersist() {
        createdTimeStamp = LocalDateTime.now();
        lastUpdatedTimeStamp = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        lastUpdatedTimeStamp = LocalDateTime.now();
    }

    /**
     *
     */
    public AbstractIdentifiedEntity() {
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Long getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }

    /**
     *
     * @param lastUpdatedTimeStamp
     */
    public void setLastUpdatedTimeStamp(LocalDateTime lastUpdatedTimeStamp) {
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    /**
     *
     * @param createdTimeStamp
     */
    public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}
