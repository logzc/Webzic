package com.logzc.webzic.orm.stmt.query;

import com.logzc.webzic.orm.dialect.Dialect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lishuang on 2016/8/29.
 */
public class Sort {


    private static final Direction DEFAULT_DIRECTION = Direction.ASC;
    private List<Order> orders;


    public Sort(Order... orders) {
        this(Arrays.asList(orders));
    }

    public Sort(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("orders cannot be null or empty");
        }
        this.orders = orders;
    }

    public Sort(String... names) {
        this(DEFAULT_DIRECTION, names);
    }

    public Sort(Direction direction, String... names) {
        this(direction, names == null ? new ArrayList<String>() : Arrays.asList(names));
    }

    public Sort(Direction direction, List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("Names cannot be null");
        }

        this.orders = new ArrayList<>(names.size());

        for (String name : names) {
            this.orders.add(new Order(direction, name));
        }
    }

    public Sort and(Sort sort) {
        if (sort == null) {
            return this;
        }

        List<Order> these = new ArrayList<>(this.orders);
        these.addAll(sort.orders);
        return new Sort(these);
    }

    public String getStatement(Dialect dialect) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            if (i == 0) {
                sb.append(order.getStatement(dialect));
            } else {
                sb.append(",").append(order.getStatement(dialect));
            }
        }

        return sb.toString();
    }

    public static class Order {

        private final String name;
        private final Direction direction;


        public Order(Direction direction, String name) {
            this.name = name;
            this.direction = direction;
        }

        public String getStatement(Dialect dialect) {

            return dialect.getSqlColumn(name) + " " + direction.name();
        }
    }


    public static enum Direction {
        ASC, DESC;

        public static Direction fromString(String value) {
            try {
                return Direction.valueOf(value.toUpperCase());
            } catch (Exception e) {
                throw new IllegalArgumentException("cannot convert to Direction.");
            }
        }
    }
}
