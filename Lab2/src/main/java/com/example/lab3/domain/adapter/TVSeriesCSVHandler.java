package com.example.lab2.domain.adapter;

import com.example.lab2.domain.factories.concrete_implementation.Watch.Anime;
import com.example.lab2.domain.factories.concrete_implementation.Watch.TVSeries;
import com.example.lab2.domain.models.datasets.tv_series_dataset.tv_shows_data.TVShowsDataset;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TVSeriesCSVHandler extends CSVHandler{

    static String[] HEADERs = {"Title","Genre","Premiere","No_of_Seasons","No_of_Episodes"};

    @Override
    public List<TVShowsDataset> parseCSV(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<TVShowsDataset> tvSeries = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                TVShowsDataset tvSeries1 = new TVShowsDataset(
                        csvRecord.get("Title"),
                        csvRecord.get("Genre"),
                        csvRecord.get("Premiere"),
                        Integer.parseInt(csvRecord.get("No_of_Seasons")),
                        Integer.parseInt(csvRecord.get("No_of_Episodes"))
                );

                tvSeries.add(tvSeries1);
            }

            return tvSeries;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
