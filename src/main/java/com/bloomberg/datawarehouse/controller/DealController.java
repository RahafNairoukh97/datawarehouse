package com.bloomberg.datawarehouse.controller;

import com.bloomberg.datawarehouse.entity.Deal;
import com.bloomberg.datawarehouse.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deal")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @GetMapping
    public ResponseEntity<List<Deal>> listDeals() {
        final List<Deal> deals = dealService.list();
        return ResponseEntity.status(HttpStatus.OK).body(deals);
    }

    @PostMapping
    public ResponseEntity<String> createDeal(@RequestBody @Valid final Deal deal) {
        dealService.createDeal(deal);
        return ResponseEntity.status(HttpStatus.CREATED).body("Deal created successfully.");
    }
}
