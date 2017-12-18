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
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

import static org.hibernate.jpa.criteria.ValueHandlerFactory.isNumeric;

public class PredicatesCriteria {

    private SearchCriteria criteria;

    public PredicatesCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate(PathBuilder<?> entityPath) {
        if (isNumeric(criteria.getValue().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":" :
                    return path.eq(value);
                case ">" :
                    return path.goe(value);
                case "<" :
                    return path.loe(value);
            }
        } else {
            StringPath path = entityPath.getString(criteria.getKey());
            switch (criteria.getOperation()) {
                case ":" :
                    return path.eq(criteria.getValue().toString());
                case "%" :
                    return path.like(criteria.getValue().toString());
            }
        }
        return null;
    }
}
