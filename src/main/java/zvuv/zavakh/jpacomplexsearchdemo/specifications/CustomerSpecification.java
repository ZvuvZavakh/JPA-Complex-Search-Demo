package zvuv.zavakh.jpacomplexsearchdemo.specifications;

import org.springframework.data.jpa.domain.Specification;
import zvuv.zavakh.jpacomplexsearchdemo.domain.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CustomerSpecification implements Specification<Customer> {

    private SearchCriteria searchCriteria;

    public CustomerSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchCriteria.getOperation().equalsIgnoreCase(FilteringTypes.GREATER_THEN_OR_EQUAL_TO.getValue())) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString()
            );
        } else if (searchCriteria.getOperation().equalsIgnoreCase(FilteringTypes.GREATER_THEN.getValue())) {
            return criteriaBuilder.greaterThan(
                    root.get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString()
            );
        } else if (searchCriteria.getOperation().equalsIgnoreCase(FilteringTypes.LESS_THEN_OR_EQUAL_TO.getValue())) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString()
            );
        } else if (searchCriteria.getOperation().equalsIgnoreCase(FilteringTypes.LESS_THEN.getValue())) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString()
            );
        } else if (searchCriteria.getOperation().equalsIgnoreCase(FilteringTypes.LIKE.getValue())) {
            return criteriaBuilder.like(
                    root.get(searchCriteria.getKey()),
                    "%" + searchCriteria.getValue() + "%"
            );
        } else if (searchCriteria.getOperation().equalsIgnoreCase(FilteringTypes.EQUALS.getValue())) {
            return criteriaBuilder.equal(
                    root.get(searchCriteria.getKey()),
                    searchCriteria.getValue()
            );
        } else if (searchCriteria.getOperation().equalsIgnoreCase(FilteringTypes.NOT_EQUAL.getValue())) {
            return criteriaBuilder.notEqual(
                    root.get(searchCriteria.getKey()),
                    searchCriteria.getValue()
            );
        } else if (searchCriteria.getOperation().equalsIgnoreCase(FilteringTypes.IN.getValue())) {
            return criteriaBuilder.in(
                    root.get(searchCriteria.getKey()).in(searchCriteria.getValue())
            );
        }

        return null;
    }
}
