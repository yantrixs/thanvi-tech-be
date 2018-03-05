package com.tanvi.tech.tanvitechbe.security.service;

import com.tanvi.tech.tanvitechbe.model.SellStock;
import com.tanvi.tech.tanvitechbe.model.Stock;
import com.tanvi.tech.tanvitechbe.repository.SellStockRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellService implements ISellService{

    private final SellStockRepository repository;
    private final StockService stockService;

    public SellService(final SellStockRepository sellStockRepository,
                       final StockService stockService) {
        this.repository = sellStockRepository;
        this.stockService = stockService;
    }

    @Override
    public List<SellStock> findAll() {
        return repository.findAll();
    }

    @Override
    public SellStock update(String id, SellStock stock) {
        stock.setId(id);

        final SellStock saved = repository.findOne(id);

        if (saved != null) {
            stock.setCreatedAt(saved.getCreatedAt());
            stock.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            stock.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(stock);
        return stock;
    }

    @Override
    public String delete(String id) {
        repository.delete(id);
        return id;
    }

    @Override
    public SellStock create(SellStock sellStocks) {
        List<Stock> stocks = new ArrayList<Stock>();
        for (Stock stock : sellStocks.getStocks()){
            stocks.add(stock);
            stock.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        stockService.updateStocks(stocks);
        return repository.save(sellStocks);
    }

    @Override
    public SellStock find(String id) {
        return repository.findOne(id);
    }
}
