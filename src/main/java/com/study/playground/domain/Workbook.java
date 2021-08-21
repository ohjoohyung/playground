package com.study.playground.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Workbook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "workbook", cascade = CascadeType.PERSIST)
//    private List<Card> cards = new ArrayList<>();

//    @OneToMany(mappedBy = "workbook", cascade = CascadeType.REMOVE)
//    private List<Card> cards = new ArrayList<>();

//    @OneToMany(mappedBy = "workbook", cascade = CascadeType.PERSIST, orphanRemoval = true)
//    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "workbook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "workbook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkbookTag> workbookTags = new ArrayList<>();

    public Workbook() {
    }

    public Workbook(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<WorkbookTag> getWorkbookTags() {
        return workbookTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workbook workbook = (Workbook) o;
        return Objects.equals(id, workbook.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
