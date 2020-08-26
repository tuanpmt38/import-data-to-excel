package excel.demo.repository.spec;

import excel.demo.dto.QueryProductDto;
import excel.demo.entity.Product;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by TuanPM on 2020-08-26.
 **/
public class ProductSpec {

    public static Specification<Product> findAllProducts(QueryProductDto data) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(data.getTitle())) {
                predicates.add(criteriaBuilder.equal(root.get("title"), data.getTitle()));
            }

            if ((data.getSizeProduct() != null)) {
                predicates.add(criteriaBuilder.equal(root.get("size"), data.getSizeProduct()));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
