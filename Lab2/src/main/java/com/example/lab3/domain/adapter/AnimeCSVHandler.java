package com.example.lab2.domain.adapter;

import com.example.lab2.domain.factories.concrete_implementation.Watch.Anime;
import com.example.lab2.domain.models.datasets.anime_dataset.AnimeDataset.AnimeDataset;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AnimeCSVHandler extends CSVHandler{
    static String[] HEADERs = {"anime_id","name","genre","type","episodes","rating","members"};

    @Override
    public List<AnimeDataset> parseCSV(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<AnimeDataset> animes = new ArrayList<AnimeDataset>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                AnimeDataset anime = new AnimeDataset(
                        Long.parseLong(csvRecord.get("anime_id")),
                        csvRecord.get("name"),
                        csvRecord.get("genre"),
                        csvRecord.get("type"),
                        Integer.parseInt(csvRecord.get("episodes")),
                        Integer.parseInt(csvRecord.get("rating")),
                        Integer.parseInt(csvRecord.get("members")));

                animes.add(anime);
            }

            return animes;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
