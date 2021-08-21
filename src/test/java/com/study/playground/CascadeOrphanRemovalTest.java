package com.study.playground;

import com.study.playground.domain.Card;
import com.study.playground.domain.Tag;
import com.study.playground.domain.Workbook;
import com.study.playground.domain.WorkbookTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CascadeOrphanRemovalTest {

    @Autowired
    private TestEntityManager entityManager;

    private Workbook workbook;

    private Card card;

    @BeforeEach
    void setUp() {
        workbook = new Workbook("Workbook");
        card = new Card("Question", "Answer");
        card.setWorkbook(workbook);
    }

//    @Test
//    @DisplayName("cascade persist를 사용해 workbook이 저장될 때 card도 같이 저장되도록 한다.")
//    void saveWorkbookWithCardWhenCascadePersist() {
//        entityManager.persist(workbook);
//        entityManager.flush();
//        entityManager.clear();
//
//        final Workbook findWorkbook = entityManager.find(Workbook.class, workbook.getId());
//        assertThat(findWorkbook.getCards())
//                .contains(card);
//    }
//
//    @Test
//    @DisplayName("cascade persist를 사용한 상태에서 card를 삭제하게 될 경우 flush를 해도 card delete 쿼리가 날라가지 않는다.")
//    void deleteWorkbookWithCardWhenCascadePersist() {
//        entityManager.persist(workbook);
//        entityManager.flush();
//        entityManager.clear();
//
//        final Workbook findWorkbook = entityManager.find(Workbook.class, workbook.getId());
//        List<Card> cards = findWorkbook.getCards();
//
//        for (Card c : cards) {
//            entityManager.remove(c);
//        }
//        entityManager.flush();
//
//        assertThat(cards.size()).isEqualTo(1);
//    }

//    @Test
//    @DisplayName("cascade persist를 사용한 상태에서 card를 삭제하게 될 경우 cards에서 remove도 해주어야 delete 쿼리문이 날라간다.")
//    void deleteWorkbookWithCardWhenCascadePersist() {
//        entityManager.persist(workbook);
//        entityManager.flush();
//        entityManager.clear();
//
//        final Workbook findWorkbook1 = entityManager.find(Workbook.class, workbook.getId());
//        List<Card> cards = findWorkbook1.getCards();
//
//        for (Card c : cards) {
//            entityManager.remove(c);
//        }
//        cards.clear();
//
//        entityManager.flush();
//
//        final Workbook findWorkbook2 = entityManager.find(Workbook.class, workbook.getId());
//
//        assertThat(findWorkbook2.getCards().size()).isEqualTo(0);
//    }

//    @Test
//    @DisplayName("cascade remove를 사용해 workbook을 삭제하면 card도 삭제된다.")
//    void deleteWorkbookWithCardWhenCascadeRemove() {
//        entityManager.persist(workbook);
//        entityManager.persist(card);
//        entityManager.flush();
//
//        entityManager.remove(workbook);
//        entityManager.flush();
//        entityManager.clear();
//
//        assertThat(entityManager.find(Card.class, card.getId()))
//                .isNull();
//    }

//    @Test
//    @DisplayName("cascade remove를 사용한 상태에서 card를 삭제하면 card가 삭제된다.")
//    void deleteCardWhenCascadePersist() {
//        entityManager.persist(workbook);
//        entityManager.persist(card);
//        entityManager.flush();
//
//        entityManager.remove(card);
//        entityManager.flush();
//        entityManager.clear();
//
//        assertThat(entityManager.find(Card.class, card.getId()))
//                .isNull();
//    }

//    @Test
//    @DisplayName("cascade remove를 사용한 상태에서 cards의 card를 remove하면 card가 삭제되는 것은 아니다.")
//    void deleteCardWhenCascadePersist() {
//        entityManager.persist(workbook);
//        entityManager.persist(card);
//        entityManager.flush();
//
//        entityManager.remove(card);
//        entityManager.flush();
//        entityManager.clear();
//
//        assertThat(entityManager.find(Card.class, card.getId()))
//                .isNull();
//    }

//    @Test
//    @DisplayName("orphanRemoval = true만 사용한 상태에서 workbook을 삭제하면 card도 삭제된다.")
//    void deleteWorkbookWhenOrphanTrue() {
//        entityManager.persist(workbook);
//        entityManager.persist(card);
//        entityManager.flush();
//
//        entityManager.remove(workbook);
//        entityManager.flush();
//
//        assertThat(entityManager.find(Card.class, card.getId()))
//                .isNull();
//    }
//
//    @Test
//    @DisplayName("orphanRemoval = true만 사용한 상태에서 cards의 card를 remove하면 delete 쿼리문이 나가지 않는다." +
//            "주의: 버그임. 참고: https://www.inflearn.com/questions/137740")
//    void deleteCardWhenOrphanTrue() {
//        entityManager.persist(workbook);
//        entityManager.persist(card);
//        entityManager.flush();
//
//        final Workbook findWorkbook1 = entityManager.find(Workbook.class, workbook.getId());
//        findWorkbook1.getCards().clear();
//        entityManager.flush();
//        entityManager.clear();
//
//        final Workbook findWorkbook2 = entityManager.find(Workbook.class, workbook.getId());
//        assertThat(findWorkbook2.getCards().size()).isEqualTo(0);
//    }

    @Test
    @DisplayName("cascade persist, orphanRemoval = true를 사용한 상태에서 workbook을 삭제하면 card도 삭제된다.")
    void deleteWorkbookWhenCascadePersistAndOrphanTrue() {
        entityManager.persist(workbook);
        entityManager.flush();

        entityManager.remove(workbook);
        entityManager.flush();

        assertThat(entityManager.find(Card.class, card.getId()))
                .isNull();
    }

    @Test
    @DisplayName("cascade persist, orphanRemoval = true를 사용한 상태에서 cards의 card를 remove하면 delete 쿼리문이 나간다.")
    void deleteCardWhenCascadePersistAndOrphanTrue() {
        entityManager.persist(workbook);
        entityManager.flush();

        final Workbook findWorkbook1 = entityManager.find(Workbook.class, workbook.getId());
        List<Card> cards = findWorkbook1.getCards();
        cards.clear();
        entityManager.flush();

        final Workbook findWorkbook2 = entityManager.find(Workbook.class, workbook.getId());
        assertThat(findWorkbook2.getCards().size()).isEqualTo(0);
    }


    @Test
    @DisplayName("중간테이블과 1:N 관계를 가지고 있는 엔티티 양쪽에 cascade all, orphanRemoval = true를 걸면 제대로 작동하지 않는 경우가 발생한다.")
    void deleteWorkbookTagWhenBothCascadeAllOrphanTrue() {
        WorkbookTag workbookTag = new WorkbookTag();
        Tag tag = new Tag();
        workbookTag.setWorkbook(workbook);
        workbookTag.setTag(tag);

        //workbookTag와 1:N 관계인 엔티티를 영속화
        entityManager.persist(workbook);
        entityManager.persist(tag);
        entityManager.flush();
        entityManager.clear();

        final Workbook findWorkbook1 = entityManager.find(Workbook.class, workbook.getId());
        List<WorkbookTag> workbookTags = findWorkbook1.getWorkbookTags();

        //workbookTags에 참조되어있는 tag method를 사용함으로써 지연로딩으로 selete문을 날리게됨. + tag가 영속화됨.
        workbookTags.get(0).getTag().getWorkbookTags().size();

        //workbookTag 삭제
        workbookTags.clear();

        entityManager.flush();
        entityManager.clear();

        final Workbook findWorkbook2 = entityManager.find(Workbook.class, workbook.getId());

        //workbookTag는 삭제되지 않았다.
        assertThat(findWorkbook2.getWorkbookTags().size()).isEqualTo(1);
    }
}