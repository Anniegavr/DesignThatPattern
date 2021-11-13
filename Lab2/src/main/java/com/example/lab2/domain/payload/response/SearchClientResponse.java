package com.example.lab2.domain.payload.response;

import com.example.lab2.domain.payload.request.SearchAllOwnersRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchClientResponse {
    private  List<SearchAllOwnersRequest> usersFound = new ArrayList<>();

}
