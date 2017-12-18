/*
 * This file is part of the EZ2 Accounting Project.
 * Author :
 * - Ivan Aribanilia
 * - Lukman Arizal
 * - Aristion Rizki
 * - Gabriel Sebastian
 *
 * Copyright (c) 2017. EZ2 Company.
 * Inc. All Rights Reserved. Terms of Use, Privacy and Trademark Guidelines
 */

package com.ez2.acc.framework.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;

import java.util.ArrayList;
import java.util.List;

public class PredicatesBuilder {
    private List<SearchCriteria> params;

    public PredicatesBuilder() {
        params = new ArrayList<>();
    }

    public PredicatesBuilder add(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build(PathBuilder<?> entityPath) {
        if (params.size() == 0)
            return null;

        List<BooleanExpression> predicates = new ArrayList<>();
        PredicatesCriteria predicate;
        for (SearchCriteria param : params) {
            predicate = new PredicatesCriteria(param);
            BooleanExpression e = predicate.getPredicate(entityPath);
            if (e != null) {
                predicates.add(e);
            }
        }

        BooleanExpression result = predicates.get(0);
        for (int i = 0; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        return result;
    }
}
