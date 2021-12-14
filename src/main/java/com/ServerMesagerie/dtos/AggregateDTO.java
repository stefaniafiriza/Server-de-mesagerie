package com.ServerMesagerie.dtos;

public class AggregateDTO<T, D> {

    public T item1;
    public D item2;

    public AggregateDTO(){}
    public AggregateDTO(T item1, D item2){
        this.item1 = item1;
        this.item2 = item2;
    }

}
