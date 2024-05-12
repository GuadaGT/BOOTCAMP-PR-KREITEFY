package com.kreitek.kreitefy.kreitefy.infraestructure.specs.specifications;
import com.kreitek.kreitefy.kreitefy.domain.entity.Song;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.EntitySpecification;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SongSpecification extends EntitySpecification<Song> implements Specification<Song> {

    public SongSpecification(List<SearchCriteria> searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria param : criteria) {
            if (param.getKey().equals("style")) {
                predicates.add(builder.equal(root.get("style").get("style"), param.getValue()));
            } else if (param.getKey().equals("album")) {
                predicates.add(builder.equal(root.get("album").get("title"), param.getValue()));
            } else if (param.getKey().equals("artist")) {
                predicates.add(builder.equal(root.get("artist").get("name"), param.getValue()));
            } else if (param.getKey().equals("title")) {
                predicates.add(builder.equal(root.get("title"), param.getValue()));
            } else if (param.getKey().equals("reproductions")) {
                predicates.add(builder.greaterThan(root.get("reproductions"), 0));
            } else if (param.getKey().equals("rating")) {
                predicates.add(builder.greaterThan(root.get("rating"), 3));
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
