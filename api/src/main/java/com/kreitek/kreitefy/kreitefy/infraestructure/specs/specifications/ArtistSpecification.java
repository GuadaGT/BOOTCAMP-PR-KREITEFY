package com.kreitek.kreitefy.kreitefy.infraestructure.specs.specifications;

import com.kreitek.kreitefy.kreitefy.domain.entity.Artist;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.EntitySpecification;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ArtistSpecification extends EntitySpecification<Artist> implements Specification<Artist> {

    public ArtistSpecification(List<SearchCriteria> searchCriteria) {
        this.criteria = searchCriteria;
    }
}
