package com.study.playground.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class WorkbookTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Workbook workbook;

    @ManyToOne
    private Tag tag;

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
        workbook.getWorkbookTags().add(this);
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        tag.getWorkbookTags().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkbookTag that = (WorkbookTag) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public Tag getTag() {
        return tag;
    }
}
