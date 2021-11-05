package zvuv.zavakh.jpacomplexsearchdemo.services;

import org.springframework.data.domain.PageRequest;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.FilteringTypes;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.FilteringOperation;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.SearchCriteria;
import zvuv.zavakh.jpacomplexsearchdemo.web.dto.FilterDto;
import zvuv.zavakh.jpacomplexsearchdemo.web.exceptions.InvalidFilterException;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class GenericPaginationService {

    protected final Integer DEFAULT_PAGE_NUMBER = 0;
    protected final Integer DEFAULT_PAGE_SIZE = 2;
    protected final String PAGE_NUMBER_PARAMETER_NAME = "page";
    protected final String PAGE_SIZE_PARAMETER_NAME = "page_size";

    protected PageRequest getPageRequestFromParams(HashMap<String, String> params) {
        int pageNumber = DEFAULT_PAGE_NUMBER;
        int pageSize = DEFAULT_PAGE_SIZE;

        if (params.containsKey(PAGE_NUMBER_PARAMETER_NAME)) {
            pageNumber = Integer.parseInt(params.get(PAGE_NUMBER_PARAMETER_NAME));
        }

        if (params.containsKey(PAGE_SIZE_PARAMETER_NAME)) {
            pageSize = Integer.parseInt(params.get(PAGE_SIZE_PARAMETER_NAME));
        }

        return PageRequest.of(pageNumber, pageSize);
    }

    protected List<SearchCriteria> getSearchCriteriaList(FilterDto filterDto, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        HashMap<String, FilteringOperation> fieldsMap = new HashMap<>();

        Arrays.stream(fields).forEach(field -> {
            if (!field.isAnnotationPresent(Transient.class) && field.isAnnotationPresent(FilteringOperation.class)) {
                fieldsMap.put(field.getName(), field.getAnnotation(FilteringOperation.class));
            }
        });

        filterDto.getParams().forEach(condition -> {
            String fieldName = condition.getField();

            if (fieldsMap.containsKey(fieldName)) {
                List<FilteringTypes> filteringTypes = Arrays.asList(fieldsMap.get(fieldName).types());
                FilteringTypes operation = condition.getOperation();

                if (filteringTypes.contains(operation)) {
                    searchCriteriaList.add(new SearchCriteria(fieldName, operation.getValue(), condition.getValue()));
                } else {
                    throw new InvalidFilterException(
                            String.format("Operation is not supported for this field: %s -> %s", fieldName, operation)
                    );
                }
            } else {
                throw new InvalidFilterException(String.format("Invalid field name: %s", fieldName));
            }
        });

        return searchCriteriaList;
    }
}
