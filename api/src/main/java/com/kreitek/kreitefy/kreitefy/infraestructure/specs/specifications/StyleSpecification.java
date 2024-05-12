package com.kreitek.kreitefy.kreitefy.infraestructure.specs.specifications;

import com.kreitek.kreitefy.kreitefy.domain.entity.Style;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.EntitySpecification;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchCriteria;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchOperation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StyleSpecification extends EntitySpecification<Style>  implements Specification<Style> {

    public StyleSpecification(List<SearchCriteria> searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Style> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : this.criteria) {
            if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}

