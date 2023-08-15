package ru.shutov.cft.arguments;

import java.util.Objects;

public class Arguments {
    private Type type;
    private Order order = Order.ASCEND;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "SortArguments{" +
                "type=" + type +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arguments arguments = (Arguments) o;
        return type == arguments.type && order == arguments.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, order);
    }
}
