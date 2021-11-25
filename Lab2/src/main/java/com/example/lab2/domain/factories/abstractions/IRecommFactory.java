package com.example.lab2.domain.factories.abstractions;

import com.example.lab2.domain.factories.concrete_implementation.Watch.TVSeries;

import java.util.List;

public abstract class IRecommFactory{

    public abstract List<TVSeries> returnRecommendation();


}
