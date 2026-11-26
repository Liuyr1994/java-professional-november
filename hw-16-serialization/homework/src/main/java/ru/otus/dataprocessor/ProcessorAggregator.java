package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.*;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        // группирует выходящий список по name, при этом суммирует поля value

        Map<String, Double> measurements = new TreeMap<>();
        for (Measurement measurement : data) {
            measurements.merge(measurement.name(), measurement.value(), Double::sum);
        }
        return Collections.unmodifiableMap(measurements);
    }
}
