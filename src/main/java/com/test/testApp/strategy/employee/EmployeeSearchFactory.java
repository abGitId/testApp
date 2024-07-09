package com.test.testApp.strategy.employee;

import com.test.testApp.enums.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Component
public class EmployeeSearchFactory {

    private Map<SearchType, IEmployeeSearchStrategy> searchStrategyMap;

    @Autowired
    public EmployeeSearchFactory(Set<IEmployeeSearchStrategy> strategySet) {
        populateSearchType(strategySet);
    }

    private void populateSearchType(Set<IEmployeeSearchStrategy> strategySet) {
        searchStrategyMap = new EnumMap<>(SearchType.class);
        strategySet.forEach(searchType -> searchStrategyMap.put(searchType.getSearchType(), searchType));
    }

    public IEmployeeSearchStrategy getEmployeeSearchStrategy(SearchType searchType) {
        return searchStrategyMap.get(searchType);
    }
}
