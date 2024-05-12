package com.kreitek.kreitefy.kreitefy.infraestructure.specs.specifications;

import com.kreitek.kreitefy.kreitefy.domain.entity.Album;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.EntitySpecification;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class AlbumSpecification extends EntitySpecification<Album> implements Specification<Album> {

    public AlbumSpecification(List<SearchCriteria> searchCriteria) {
        this.criteria = searchCriteria;
    }
}
